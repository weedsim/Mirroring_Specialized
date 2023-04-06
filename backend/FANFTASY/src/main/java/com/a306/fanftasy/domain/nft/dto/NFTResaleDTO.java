package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTResaleDTO {

    private Long nftId;
    private long editionNum;
    private String content;
    private String fileType;
    private double originPrice;
    private LocalDateTime regDate;
    private String title;
    private String fileCID;

    public static NFTResaleDTO fromEntity(NFT nft, NFTSource nftSource) {
        return NFTResaleDTO.builder()
                .nftId(nft.getNftId())
                .editionNum(nft.getEditionNum())
                .content(nftSource.getContent())
                .fileType(nftSource.getFileType())
                .originPrice(nftSource.getOriginPrice())
                .regDate(nftSource.getRegDate())
                .title(nftSource.getTitle())
                .fileCID(nftSource.getFileCID())
                .build();
    }
}
