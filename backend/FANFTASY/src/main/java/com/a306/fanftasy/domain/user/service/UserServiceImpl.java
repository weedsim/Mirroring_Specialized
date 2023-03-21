package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import com.a306.fanftasy.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    public UserLoginDTO login(String address){
        User user = userRepository.findByAddress(address).orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
        String nickname = user.getNickname();
        String accessToken = jwtTokenUtil.generateAccessToken(nickname);
       // String refreshToken = jwtTokenUtil.generateRefreshToken(nickname);
        return UserLoginDTO.of(nickname,accessToken);


        }

    @Override
    public void join(UserJoinDTO userJoinDTO) {
           userRepository.save(User.ofUser(userJoinDTO));
    }

    @Override
    public UserDetailDTO getUserDetail(String addresss){
        User user = userRepository.findByAddress(addresss).orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
        return UserDetailDTO.builder()
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .phone(user.getPhone())
                .role(user.getRole())
                .totalPrice(user.getTotalPrice())
                .totalSales(user.getTotalSales())
                .build();
    }

}
