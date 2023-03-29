package com.a306.fanftasy.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String address;
    private String profileImg;
    private String nickname;
    public static UserLoginDTO of(String nickname,String address,String profileImg){
        return UserLoginDTO.builder()
                .nickname(nickname)
                .address(address)
                .profileImg(profileImg)
                .build();
    }
}
