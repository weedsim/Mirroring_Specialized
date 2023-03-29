package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTListDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface NFTService {

  void addNFT(MultipartFile file, NFTCreateDTO nftCreateDTO);


  NFTDetailDTO getNFT(long nftId);

  void modifyNFT(NFTTradeDTO nftTradeDTO);


  List<NFTListDTO> getNFTListByOwnerId(long regArtistId);

}
