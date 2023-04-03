/*
 *  NFT 생성할때 정보 받아오는 DTO
 * */
package com.a306.fanftasy.domain.nft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTCreateDTO {

  private String title;
  private String content;
  private long totalNum; //발행량
  private double originPrice; //발행가
  private long regArtistId;
  private String fileType; //파일 종류
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime regDate;
  //    private List<String> tokenUris;
  private String fileCID; //파일이 IPFS에 저장된 CID
  private String metaCID; //메타데이터(NFT에 대한 정보)가 IPFS에 저장된 CID

//  public static NFTCreateDTO fromJSON(JSONObject jsonObject){
//    return NFTCreateDTO.builder()
//        .title(jsonObject.get("title").toString())
//  }
}
