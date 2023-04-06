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
  private String fileCID; //파일 uri
  private String fileType; //파일 종류
  private long likeNum;
  private boolean userLike;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime regDate;
  private long saleNftId; //클릭시 판매될 NFTID
  private String saleContractAddress; //클릭시 판매될 SaleContractAddress
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
        .fileCID(nftSource.getFileCID())
        .fileType(nftSource.getFileType())
        .saleNftId(0) //기본값
        .saleContractAddress("sold out") //기본값
        .build();
  }
  public void updateUserLike(boolean userLike){
    this.userLike = userLike;
  }
}
