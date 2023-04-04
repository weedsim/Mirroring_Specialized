package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.*;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface NFTService {

  void addNFT(NFTCreateDTO nftCreateDTO) throws IOException;


  NFTDetailDTO getNFT(long nftId);

  void modifyNFT(NFTTradeDTO nftTradeDTO);


  List<NFTListDTO> getNFTListByOwnerId(long regArtistId);

  List<NFTMarketListDTO> getNFTList(int orderType, int saleType, String keyword);
}
