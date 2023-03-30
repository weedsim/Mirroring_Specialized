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
public class NFTSourceListDTO {
    private long nftSourceId;
    private UserPublicDTO regArtist;
    private String title;
    private double originPrice;
    //아티스트 정보 중 필요한 것만
    private long totalNum;
    private long remainNum;
    private long likeNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regDate;
    private String fileCID;
    private String fileType;
    public static NFTSourceListDTO fromEntity(NFTSource nftSource){
        return NFTSourceListDTO.builder()
            .nftSourceId(nftSource.getNftSourceId())
            .regArtist(UserPublicDTO.fromEntity(nftSource.getRegArtist()))
            .title(nftSource.getTitle())
            .originPrice(nftSource.getOriginPrice())
            .totalNum(nftSource.getTotalNum())
            .remainNum(nftSource.getRemainNum())
            .likeNum(nftSource.getLikeNum())
            .regDate(nftSource.getRegDate())
            .fileCID(nftSource.getFileCID())
            .fileType(nftSource.getFileType())
            .build();
    }
}
