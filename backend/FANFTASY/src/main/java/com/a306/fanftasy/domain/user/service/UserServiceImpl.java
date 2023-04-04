package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.nft.service.EthereumService;
import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.dto.UserUpdateDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import com.a306.fanftasy.util.JwtTokenUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtTokenUtil jwtTokenUtil;
  private final S3Service s3Service;
  private final EthereumService ethereumService;

    private final UserSecurityService userSecurityService;

    public UserLoginDTO login(String address){
        User user = userRepository.findByAddress(address);
        if(user!=null){
                  try {
                Long userId=user.getUserId();
                String nickname=userSecurityService.aesDecrypt(user.getNickname(),userSecurityService.hexToByteArray(user.getUserKey()));
                String profileImg=userSecurityService.aesDecrypt(user.getProfileImg(),userSecurityService.hexToByteArray(user.getUserKey()));
                String role = userSecurityService.aesDecrypt(user.getRole(),userSecurityService.hexToByteArray(user.getUserKey()));
                return UserLoginDTO.of(userId,nickname,address,role,profileImg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
        else{
            return null;
        }
    }

    @Override
    public void join(UserJoinDTO userJoinDTO)   {
        UserJoinDTO userencrypt=new UserJoinDTO();
        try {
            byte[] key=userSecurityService.generateKey("AES",128);
            userencrypt.setAddress(userJoinDTO.getAddress());
            userencrypt.setName(userSecurityService.aesEncrypt(userJoinDTO.getName(),key));
            userencrypt.setNickname(userSecurityService.aesEncrypt(userJoinDTO.getNickname(),key));
            userencrypt.setEmail(userSecurityService.aesEncrypt(userJoinDTO.getEmail(),key));
            userencrypt.setPhone(userSecurityService.aesEncrypt(userJoinDTO.getPhone(),key));
            userencrypt.setRole(userSecurityService.aesEncrypt(userJoinDTO.getRole(),key));
            userencrypt.setCompany(userSecurityService.aesEncrypt(userJoinDTO.getCompany(),key));
            userencrypt.setProfileImg(userSecurityService.aesEncrypt(userJoinDTO.getProfileImg(),key));
            userencrypt.setKey(userSecurityService  .byteArrayToHex(key));
            //userJoinDTO.setNickname(userencrypt.getNickname());
            // log.info("Nickname: "+userJoinDTO.getNickname());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        userRepository.save(User.ofUser(userencrypt));
    }

    @Override
    public UserDetailDTO getUserDetail(String addresss) throws IOException {
        User user = userRepository.findByAddress(addresss);
        double balance = ethereumService.getBalance(addresss);
        try {
            return UserDetailDTO.builder()
                    .name(userSecurityService.aesDecrypt(user.getName(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .address(userSecurityService.aesDecrypt(user.getAddress(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .email(userSecurityService.aesDecrypt(user.getEmail(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .nickname(userSecurityService.aesDecrypt(user.getNickname(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .profileImg(user.getProfileImg())
                    .phone(userSecurityService.aesDecrypt(user.getPhone(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .role(userSecurityService.aesDecrypt(user.getRole(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .totalPrice(user.getTotalPrice())
                    .totalSales(user.getTotalSales())
                    .balance(balance)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findByAddress(userUpdateDTO.getAddress());
        byte[] key=userSecurityService.hexToByteArray(user.getUserKey());
        log.info(userUpdateDTO.getNickname());
        try {
            user.setAddress(userSecurityService.aesEncrypt(userUpdateDTO.getAddress(),key));
            user.setEmail(userSecurityService.aesEncrypt(userUpdateDTO.getEmail(),key));
            user.setNickname(userSecurityService.aesEncrypt(userUpdateDTO.getNickname(),key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        if (StringUtils.hasText(userUpdateDTO.getNickname())) {
//            user.setNickname(userUpdateDTO.getNickname());
//        }
//        if (StringUtils.hasText(userUpdateDTO.getProfileImg())) {
//            user.setProfileImg(userUpdateDTO.getProfileImg());
//        }

    userRepository.save(user);
  }

  @Override
  public User findByUserId(Long userId) {
    User user = userRepository.findByUserId(userId);
    return user;
  }

  @Override
  public void logout(Long userId) {

  }


}
