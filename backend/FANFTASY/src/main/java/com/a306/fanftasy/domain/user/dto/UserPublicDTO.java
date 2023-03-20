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
public class UserPublicDTO {
  private long userId;
  private String profileImg;
  private String nickname;

  public UserPublicDTO(User user){
    this.userId = user.getUserId();
    this.profileImg = user.getProfileImg();
    this.nickname = user.getNickname();
  }

  public static UserPublicDTO fromEntity(User user){
    return UserPublicDTO.builder()
        .userId(user.getUserId())
        .profileImg(user.getProfileImg())
        .nickname(user.getNickname())
        .build();
  }
}
