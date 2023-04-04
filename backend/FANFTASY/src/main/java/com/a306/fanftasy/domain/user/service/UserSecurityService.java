package com.a306.fanftasy.domain.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
@Service
@Slf4j
public class UserSecurityService {

    @Value("${user.secretKey}")
    private String SECRET_KEY;


    public  byte[] generateKey(String algorithm,int keySize) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }
    public  String aesEncrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,
                skeySpec,
                new IvParameterSpec(SECRET_KEY.getBytes()));
        byte[] encrypted = cipher.doFinal(msg.getBytes());
        return  byteArrayToHex(encrypted);
    }

    public  String aesDecrypt(String msg,byte[] key ) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,
                skeySpec,
                new IvParameterSpec(SECRET_KEY.getBytes()));
        byte[] encrypted = hexToByteArray(msg);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public  byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }
    public  String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

}
