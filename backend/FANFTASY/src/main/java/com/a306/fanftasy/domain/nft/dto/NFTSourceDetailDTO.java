/*
 *  NFT 정보를 조회했을때 반환해 줄 DTO
 * */

package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTSourceDetailDTO {
  private long nftSourceId;
  private String title;
  private String content; //내용
  private long totalNum; //발행량
  private long remainNum;
  private double originPrice; //발행가
  private UserPublicDTO regArtist;
  private String fileUri; //파일 uri
  private String fileType; //파일 종류
  private long likeNum;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime regDate;
  public static NFTSourceDetailDTO fromEntity(NFTSource nftSource){
    return NFTSourceDetailDTO.builder()
        .nftSourceId(nftSource.getNftSourceId())
        .regArtist(UserPublicDTO.fromEntity(nftSource.getRegArtist()))
        .title(nftSource.getTitle())
        .content(nftSource.getContent())
        .originPrice(nftSource.getOriginPrice())
        .totalNum(nftSource.getTotalNum())
        .remainNum(nftSource.getRemainNum())
        .likeNum(nftSource.getLikeNum())
        .regDate(nftSource.getRegDate())
        .fileUri(nftSource.getFileUri())
        .fileType(nftSource.getFileType())
        .build();
  }
}
