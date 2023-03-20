package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NFTServiceImpl implements NFTService {

  private final NFTRepository nftRepository;

  @Override
  public void addNFT(NFTCreateDTO nftCreateDTO) {
    try {
      long artistId = nftCreateDTO.getRegArtistId();
      log.info(String.valueOf(artistId));
      User artist = User.builder().userId(artistId).build(); //이 부분 수정 필요
      NFT nft = NFT.builder()
          .title(nftCreateDTO.getTitle())
          .owner(artist)
          .tokenUri(nftCreateDTO.getTokenUri())
          .isOnSale(true)
          .currentPrice(nftCreateDTO.getCurrentPrice())
          .regArtist(artist)
          .regDate(nftCreateDTO.getRegDate())
          .fileUri(nftCreateDTO.getFileUri())
          .transactionTime(nftCreateDTO.getRegDate())
          .build();
      nftRepository.save(nft);
    } catch (Exception e) {
      throw e;
    }//catch
  }

  @Override
  public NFTDetailDTO getNFT(long nftId) {
    try {
      NFT nft = nftRepository.findById(nftId);
      NFTDetailDTO nftDetailDTO = NFTDetailDTO.builder()
          .nftId(nft.getNftId())
          .title(nft.getTitle())
          .owner(UserPublicDTO.fromEntity(nft.getOwner()))
          .tokenUri(nft.getTokenUri())
          .isOnSale(nft.getIsOnSale())
          .currentPrice(nft.getCurrentPrice())
          .regArtist(UserPublicDTO.fromEntity(nft.getRegArtist()))
          .regDate(nft.getRegDate())
          .fileUri(nft.getFileUri())
          .build();
      return nftDetailDTO;
    } catch (Exception e) {
      throw e;
    }//catch
  }

  @Override
  public void modifyNFT(NFTTradeDTO nftTradeDTO) {
    try {
      NFT nftEntity = nftRepository.findById(nftTradeDTO.getNftId());
      User owner = User.builder().userId(nftTradeDTO.getBuyerId()).build();
      nftEntity.updateIsOnSale(false);
      nftEntity.updateOwner(owner);
      nftEntity.updateTransactionTime(nftTradeDTO.getTransactionTime());
      nftRepository.save(nftEntity);
    } catch (Exception e) {
      throw e;
    }//catch
  }

  @Override
  public List<NFTDetailDTO> getNFTListByArtistId(long regArtistId) {
    try{
    User regArtist = User.builder().userId(regArtistId).build();
    List<NFT> entityList = nftRepository.findByRegArtistOrderByRegDateDesc(regArtist);

    //엔티티를 DTO로 변환
    List<NFTDetailDTO> result = entityList.stream().map(m-> NFTDetailDTO.fromEntity(m)).collect(
        Collectors.toList());
    return result;
    }//try
    catch(Exception e){
      throw e;
    }//catch
  }//getNFTListByArtist


  @Override
  public List<NFTDetailDTO> getNFTListByOwnerId(long ownerId) {
    try{
      User owner = User.builder().userId(ownerId).build();
      List<NFT> entityList = nftRepository.findByOwnerOrderByTransactionTimeDesc(owner);

      //엔티티를 DTO로 변환
      List<NFTDetailDTO> result = entityList.stream().map(m-> NFTDetailDTO.fromEntity(m)).collect(
          Collectors.toList());
      return result;
    }//try
    catch(Exception e){
      throw e;
    }//catch
  }//getNFTListByOwnerId
}
