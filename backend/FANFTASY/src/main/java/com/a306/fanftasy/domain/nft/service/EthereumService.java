package com.a306.fanftasy.domain.nft.service;

import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

@Slf4j
@RequiredArgsConstructor
@Service
public class EthereumService {

  @Value("${blockchain.NFTContractAddress}")
  private String NFT_CONTRACT_ADDRESS;
//  @Value("${blockchain.FTContractAddress}")
//  private String FT_CONTRACT_ADDRESS;
  @Value("${blockchain.AdminPrivateKey}")
  private String ADMIN_PRIVATE_KEY;
  @Value("${blockchain.AdminAddress}")
  private String ADMIN_ADDRESS;
  @Value("${blockchain.NetworkURL}")
  private String NETWORK_URL;
  @Value("${blockchain.NetworkID}")
  private int NETWORK_ID;
  private BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
  private BigInteger GAS_PRICE = BigInteger.valueOf(0);
  private final UserRepository userRepository;

  //잔액 조회
  public double getBalance(String address) throws IOException {
    log.info("getBalance : " + address);
    try {
      //1. CONNECT WEB3
      Admin web3j = Admin.build(new HttpService(NETWORK_URL));
      //2. SEND TRANSACTION
      EthGetBalance balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
      log.info(String.valueOf(balance.getBalance().doubleValue()));
      return balance.getBalance().doubleValue();
    } catch (Exception e) {
      log.info("잔액 조회 에러 발생");
      return 0;
    }
  }

  //잔액 충전
  public double charge(long id, double ether)
      throws IOException, ExecutionException, InterruptedException {
    log.info("charge Request - requestUserId : " + id + ", amount : " + ether);
    try {
      //1. CONNECT WEB3
      Admin web3j = Admin.build(new HttpService(NETWORK_URL));
      log.info("web3j 연결 생성 완료");
      User requestUser = userRepository.findByUserId(id);
      log.info("requestUser : " + requestUser.toString());
      String address = requestUser.getAddress();
      if (ether <= 10) {
        Double weiD = ether * Math.pow(10, 18);
        log.info("weiD");
        long weiL = weiD.longValue();
        log.info("weiL");
        BigInteger wei = BigInteger.valueOf(weiL);
        //2.MAKE CREDENTIALS
        Credentials credentials = getCredentialsFromPrivateKey();
        //3.NONCE
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
            credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        log.info("nonce 생성 완료 : " + nonce);
        //4.CREATE TRANSACTION
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
            nonce, GAS_PRICE, GAS_LIMIT, address, wei);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        log.info("트랜잭션 생성 완료: " + hexValue);
        //5. SEND TRANSACTION
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
//      Thread.sleep(2000);
        log.info("트랜잭션 전송 완료 : " + transactionHash);
        //6. GET TRANSACTION RECEIPT
        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(
            transactionHash).send();
        if (transactionReceipt.getResult() == null) {
          for (int i = 0; i < 5; i++) {
            Thread.sleep(3000);
            transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();
            if (transactionReceipt.getResult() != null) {
              log.info("SUCCESS TRANSACTION");
              break;
            }
          }
        } else {
          log.info("SUCCESS TRANSACTION");
        }
        log.info("TransactionReceipt Result : " + transactionReceipt.getResult());
      }//if금액 체크
      //업데이트된 잔액 보내주기
      EthGetBalance balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
      log.info(String.valueOf(balance.getBalance().doubleValue()));
      return balance.getBalance().doubleValue();
    } catch (Exception e) {
      log.info("충전 에러 발생");
      throw e;
    }
  }

//  public Object ethCall(Function function)
//      throws IOException, ExecutionException, InterruptedException {
//    //1. CONNECT WEB3
//    Admin web3j = Admin.build(new HttpService(NETWORK_URL));
//    //2. CREATE TRANSACTION
//    Transaction transaction = Transaction.createEthCallTransaction(ADMIN_ADDRESS,
//        FT_CONTRACT_ADDRESS, FunctionEncoder.encode(function));
//    //3. CALL ETHEREUM
//    EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
//    log.info("call transaction 전송");
//    //4. DECODE RESULT
//    List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
//        function.getOutputParameters());
//    log.info("nonce : ");
//    return (String) decode.get(0).getValue();
//    // transaction에 대한 transaction Hash값 얻기.
//  }//ethCall


  public long createNFT(Function function, String contractAddress)
      throws IOException, InterruptedException, ExecutionException {
    EthGetTransactionReceipt transactionReceipt = getTransactionReceipt(function, contractAddress);
    log.info("TransactionReceipt Result : " + transactionReceipt.getResult());
    long tokenID = Long.decode(transactionReceipt.getResult().getLogs().get(0).getTopics().get(3));
    log.info("token생성 ID : " + tokenID);
    return tokenID;
  }//ethSendTransaction

  public String createSale(Function function,  String contractAddress)
      throws IOException, InterruptedException, ExecutionException {
    EthGetTransactionReceipt transactionReceipt = getTransactionReceipt(function, contractAddress);
    log.info("TransactionReceipt Result : " + transactionReceipt.getResult());
    String saleAddress = transactionReceipt.getResult().getLogs().get(0).getAddress();
    log.info("Sale생성 contractAddress : " + saleAddress);
    return saleAddress;
  }//ethSendTransaction


  public EthGetTransactionReceipt getTransactionReceipt(Function function, String contractAddress)
      throws IOException, InterruptedException, ExecutionException {
    //1. CONNECT WEB3
    log.info("ethSendTransaction 호출 : " + function.getInputParameters().toString());
    Web3j web3j = Web3j.build(new HttpService(NETWORK_URL));
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    log.info("네트워크 버전 : " + web3ClientVersion.getWeb3ClientVersion());
    //2.MAKE CREDENTIALS
    Credentials credentials = getCredentialsFromPrivateKey();
    //3.NONCE
    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
        credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
    //4.CREATE TRANSACTION
    RawTransaction rawTransaction = RawTransaction.createTransaction(
        nonce, GAS_PRICE, GAS_LIMIT, contractAddress, FunctionEncoder.encode(function));
    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
    String hexValue = Numeric.toHexString(signedMessage);
    //5. SEND TRANSACTION
    EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
    String transactionHash = ethSendTransaction.getTransactionHash();
    log.info("transactionHash : " + transactionHash);
    List<Type> decode = FunctionReturnDecoder.decode(ethSendTransaction.getResult(),
        function.getOutputParameters());
    log.info("hash value : " + decode.get(0).getValue());
    Thread.sleep(1000);
    //6. GET TRANSACTION RECEIPT
    EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash)
        .send();

    if (transactionReceipt.getResult() == null) {
      for (int i = 0; i < 20; i++) {
        Thread.sleep(1000);
        transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();
        if (transactionReceipt.getResult() != null) {
          break;
        }
      }
    }
    return transactionReceipt;
  }//ethSendTransaction

  //GET CREDENTIALS
  private Credentials getCredentialsFromPrivateKey() {
    return Credentials.create(ADMIN_PRIVATE_KEY);
  }
}//class
