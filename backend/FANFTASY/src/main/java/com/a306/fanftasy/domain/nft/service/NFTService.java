package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.web.multipart.MultipartFile;

public interface NFTService {

  void addNFT(NFTCreateDTO nftCreateDTO, LocalDateTime endDate)
      throws IOException, ExecutionException, InterruptedException;


  NFTDetailDTO getNFT(long nftId);

  void modifyNFT(NFTTradeDTO nftTradeDTO);


  List<NFTListDTO> getNFTListByOwnerId(long regArtistId, int type);

  List<NFTMarketListDTO> getNFTList(int orderType, int saleType, String keyword);

  NFTDetailDTO getNFTDetail(long nftSourceId, Long userId);

    NFTResaleDTO nftReSell(Long nftId);

  void resell(SaleDTO saleDTO);

  SaleDTO getNFTfirstSale(long nftSourceId);

  void resellCancel(long nftId);
}
