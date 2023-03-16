package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NFTService {
    void addNFT(NFTCreateDTO nftCreateDTO);

    NFTDetailDTO getNFT(int nftId);

    void modifyNFT(NFTTradeDTO nftTradeDTO);

    List<NFTDetailDTO> getNFTList(int regArtistId, int ownerId, String keyword);
}
