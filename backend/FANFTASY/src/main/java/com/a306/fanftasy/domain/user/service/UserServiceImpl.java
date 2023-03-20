package com.a306.fanftasy.domain.user.service;

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
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private String login(String address){
        User user = userRepository.findByAddress(address).orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));


        String nickname = user.getNickname();
        String accessToken = jwtTokenUtil.generateAccessToken(nickname);
        refreshToken = saveRefreshToken(nickname);
        return " ";


        }


}
