package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.like.repository.NFTLikeRepository;
import com.a306.fanftasy.domain.nft.dto.NFTListDTO;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.nft.repository.NFTSourceRepository;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NFTServiceImpl implements NFTService {

  private final NFTRepository nftRepository;
  private final NFTSourceRepository nftSourceRepository;
  private final UserRepository userRepository;
  private final NFTLikeRepository nftLikeRepository;
  //1. NFT 생성
  @Override
  public void addNFT(NFTCreateDTO nftCreateDTO) {
    try {
      long artistId = nftCreateDTO.getRegArtistId();
      log.info(String.valueOf(artistId));
      //등록 아티스트
      User artist = User.builder().userId(artistId).build(); //이 부분 수정 필요?
      //등록 갯수
      long totalNum = nftCreateDTO.getTotalNum();
      double originPrice = nftCreateDTO.getOriginPrice();
      LocalDateTime regDate = nftCreateDTO.getRegDate();
      //NFT Source생성
      NFTSource nftSource = NFTSource.builder()
          .title(nftCreateDTO.getTitle())
          .content(nftCreateDTO.getContent())
          .totalNum(totalNum)
          .originPrice(originPrice)
          .regArtist(artist)
          .fileType(nftCreateDTO.getFileType())
          .fileUri(nftCreateDTO.getFileUri())
          .regDate(regDate)
          .remainNum(totalNum)
          .likeNum(0)
          .build();
      nftSourceRepository.save(nftSource);
      log.info("nft 콘텐츠 등록 완료");
      //개별 nft 생성
      long nftSourceId = nftSource.getNftSourceId();
      for (String tokenUri : nftCreateDTO.getTokenUris()
      ) {
        NFT nft = NFT.builder()
            .owner(artist)
            .tokenUri(tokenUri)
            .isOnSale(true)
            .currentPrice(originPrice)
            .transactionTime(regDate)
            .nftSource(nftSource)
            .build();
        nftRepository.save(nft);
      }//for-each
    } catch (Exception e) {
      throw e;
    }//catch
  }

  //6. 회원 소유 NFT목록 반환
  @Override
  public List<NFTListDTO> getNFTListByOwnerId(long ownerId) {
    try{
      User owner = User.builder().userId(ownerId).build();
      List<NFT> entityList = nftRepository.findByOwnerOrderByTransactionTimeDesc(owner);

      //엔티티를 DTO로 변환
      List<NFTListDTO> result = entityList.stream().map(m-> NFTListDTO.fromEntity(m)).collect(
          Collectors.toList());
      return result;
    }//try
    catch(Exception e){
      throw e;
    }//catch
  }//getNFTListByOwnerId

  //7. 개인이 보유한 NFT 상세
  @Override
  public NFTDetailDTO getNFT(long nftId) {
    try {
      NFT nft = nftRepository.findById(nftId);
      NFTDetailDTO nftDetailDTO = NFTDetailDTO.fromEntity(nft);
      //      //좋아요 찾기
//      //securitycontext holder에서 user를 꺼내서
      UserLoginDTO userLoginDTO = (UserLoginDTO) SecurityContextHolder.getContext()
          .getAuthentication().getPrincipal();
      long userId = userLoginDTO.getUserId();
      User userEntity = userRepository.findByUserId(userId);
      boolean userLike = false;
      if (nftLikeRepository.findByNftAndUser(nft, userEntity) != null) {
        userLike = true;
      }
      //로그인된 userid와 nftsourceid 를 통해서 nftsourcelike가 존재하는지 find
      // 반환값이 null이 아니면 userLike = true;
      nftDetailDTO.updateUserLike(userLike);
      return nftDetailDTO;
    } catch (Exception e) {
      throw e;
    }//catch
  }

  //8.개인거래
  @Override
  public void modifyNFT(NFTTradeDTO nftTradeDTO) {
    try {
      NFT nftEntity = nftRepository.findById(nftTradeDTO.getNftId());
      User owner = User.builder().userId(nftTradeDTO.getBuyerId()).build();
      nftEntity.updateIsOnSale(false);
      nftEntity.updateOwner(owner);
      nftEntity.updateTransactionTime(nftTradeDTO.getTransactionTime());
      nftEntity.updateCurrentPrice(nftTradeDTO.getTransactionPrice());
      nftRepository.save(nftEntity);
    } catch (Exception e) {
      throw e;
    }//catch
  }

}
