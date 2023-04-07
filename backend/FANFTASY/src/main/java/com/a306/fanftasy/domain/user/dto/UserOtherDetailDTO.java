package com.a306.fanftasy.domain.user.dto;

import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.entity.NFT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOtherDetailDTO {

    private String profileImg; // 프로필 이미지
    private String nickname; // 닉네임
    private List<NFTDetailDTO> nftDetailDTOList; // 보유 NFT
}
