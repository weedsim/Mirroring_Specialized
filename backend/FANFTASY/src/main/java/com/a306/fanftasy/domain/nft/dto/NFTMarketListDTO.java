package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.nft.entity.NFTSource;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTMarketListDTO {

    private Long nftSourceId; // nftId
    private String fileCID; // nft token uri
    private String title; // 제목
    private String nickname; // 발행한 아티스트 이름
    private Double currentPrice; // 현재 판매 가격
    private String fileType;
    private Long like; // 좋아요 수

    public static NFTMarketListDTO fromEntity(NFTSource nftSource) {
        return NFTMarketListDTO.builder()
                .nftSourceId(nftSource.getNftSourceId())
                .fileCID(nftSource.getFileCID())
                .title(nftSource.getTitle())
                .nickname(nftSource.getRegArtist().getNickname())
                .fileType(nftSource.getFileType())
                .like(nftSource.getLikeNum())
                .build();
    }

}
