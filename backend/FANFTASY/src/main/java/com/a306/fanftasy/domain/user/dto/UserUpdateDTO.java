package com.a306.fanftasy.domain.user.dto;

import com.a306.fanftasy.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {

    private String address;
    private String email;
    private String nickname;
    private String profileImg;

    public UserUpdateDTO fromEntity(User user) {
        return UserUpdateDTO.builder()
                .address(user.getAddress())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .build();
    }

}
