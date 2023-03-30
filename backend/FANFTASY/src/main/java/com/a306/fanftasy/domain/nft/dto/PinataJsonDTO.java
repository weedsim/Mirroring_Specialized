package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PinataJsonDTO {
  private String title; //제목
  private String content; //내용
  private long totalNum; //발행량
  private double originPrice; //발행가
  private long regArtistId;
  private String fileType; //파일 종류
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime regDate;
  //    private List<String> tokenUris;
  private String fileCID;

  public static PinataJsonDTO fromCreateDTO(NFTCreateDTO nftCreateDTO, String fileCID){
    return PinataJsonDTO.builder()
        .title(nftCreateDTO.getTitle())
        .content(nftCreateDTO.getContent())
        .totalNum(nftCreateDTO.getTotalNum())
        .originPrice(nftCreateDTO.getOriginPrice())
        .regArtistId(nftCreateDTO.getRegArtistId())
        .fileType(nftCreateDTO.getFileType())
        .regDate(LocalDateTime.now())
        .fileCID(fileCID)
        .build();
  }
}
