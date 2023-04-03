package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTListDTO {
  private Long nftId;
  private UserPublicDTO owner;
  private String tokenUri;
  private NFTSourceListDTO nftSource;
  private Boolean isOnSale;
  private long editionNum;
  public static NFTListDTO fromEntity(NFT nft){
    return NFTListDTO.builder()
        .nftId(nft.getNftId())
        .owner(UserPublicDTO.fromEntity(nft.getOwner()))
        .tokenUri(nft.getTokenUri())
        .nftSource(NFTSourceListDTO.fromEntity(nft.getNftSource()))
        .isOnSale(nft.getIsOnSale())
        .editionNum(nft.getEditionNum())
        .build();
  }
}
