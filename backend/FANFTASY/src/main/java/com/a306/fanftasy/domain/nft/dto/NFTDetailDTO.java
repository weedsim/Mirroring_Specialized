/*
*  NFT 정보를 조회했을때 반환해 줄 DTO
* */

package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.nft.entity.NFT;
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
public class NFTDetailDTO {
    private Long nftId;
    private UserPublicDTO owner;
    private String saleContract;
    private double currentPrice;
    private Boolean isOnSale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime transactionTime;
    private NFTSourceDetailDTO nftSource;
    private long editionNum;
    private long nftLikeNum;
    private boolean userLike;
    public static NFTDetailDTO fromEntity(NFT nft){
        return NFTDetailDTO.builder()
            .nftId(nft.getNftId())
            .owner(UserPublicDTO.fromEntity(nft.getOwner()))
            .saleContract(nft.getSaleContract())
            .currentPrice(nft.getCurrentPrice())
            .nftSource(NFTSourceDetailDTO.fromEntity(nft.getNftSource()))
            .isOnSale(nft.getIsOnSale())
            .transactionTime(nft.getTransactionTime())
            .nftLikeNum(nft.getNftLikeNum())
            .editionNum(nft.getEditionNum())
            .build();
    }
    public void updateUserLike(boolean userLike){
        this.userLike = userLike;
    }
}
