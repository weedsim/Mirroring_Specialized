package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl {
    private String login(String address){
        User user = UserRepository.findByAddress(address).orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));


        String nickname = User.getNickname();
        String accessToken = jwtTokenUtil.generateAccessToken(nickname);
        RefreshToken refreshToken = saveRefreshToken(nickname);
        return TokenDto.of(accessToken, refreshToken.getRefreshToken());

        return true;
        }
    private void checkPassword(String rawPassword, String findMemberPassword){

        if(!passwordEncoder.matches(rawPassword, findMemberPassword)){
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다");
        }
    }

}
