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
import java.io.IOException;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

@Service
@Slf4j
@RequiredArgsConstructor
public class NFTServiceImpl implements NFTService {

  private final NFTRepository nftRepository;
  private final NFTSourceRepository nftSourceRepository;
  private final UserRepository userRepository;
  private final NFTLikeRepository nftLikeRepository;

  private final BasicService basicService;

  //1. NFT 생성
  @Override
  public void addNFT(NFTCreateDTO nftCreateDTO)
      throws IOException, ExecutionException, InterruptedException {
    try {
      log.info("---------------------------------");
      //3. NFT Source 데이터 + file CID ipfs + Metadata CID이 DTO에 담겨서 들어옴
      //4. NFT생성 스마트컨트랙트 호출
      long artistId = nftCreateDTO.getRegArtistId();
      //등록 아티스트
      User artist = userRepository.findByUserId(artistId);
      //등록 갯수
      long totalNum = nftCreateDTO.getTotalNum();
      double originPrice = nftCreateDTO.getOriginPrice();
      LocalDateTime regDate = nftCreateDTO.getRegDate();
      String metaCID = nftCreateDTO.getMetaCID();
      log.info("NFT Source 저장 시작");
      //NFT Source생성
      NFTSource nftSource = NFTSource.builder()
          .title(nftCreateDTO.getTitle())
          .content(nftCreateDTO.getContent())
          .totalNum(totalNum)
          .originPrice(originPrice)
          .regArtist(artist)
          .fileType(nftCreateDTO.getFileType())
          .fileCID(nftCreateDTO.getFileCID())
          .metaCID(metaCID)
          .regDate(regDate)
          .remainNum(totalNum)
          .likeNum(0)
          .build();
      nftSourceRepository.save(nftSource);
      log.info("nft 콘텐츠 등록 완료");

      //개별 nft 생성
      String artistAddress = artist.getAddress();

      log.info("개별 nft 생성 시작");
      for (int i = 1; i <= totalNum; i++
      ) {
        long tokenID;
        try{
        //스마트 컨트랙트로 tokenID 받아오기
          tokenID = basicService.create(artistAddress, metaCID);
        }catch (Exception e){
          log.info("토큰 발급 재실행");
          tokenID = basicService.create(artistAddress, metaCID);
        }
        NFT nft = NFT.builder()
            .nftId(tokenID)
            .owner(artist)
            .isOnSale(true)
            .currentPrice(originPrice)
            .transactionTime(regDate)
            .nftSource(nftSource)
            .editionNum(i)
            .build();
        log.info("nft generated : "+ nft.toString());
        nftRepository.save(nft);
      }//for-each
    } catch (Exception e) {
      throw e;
    }//catch
  }

  //6. 회원 소유 NFT목록 반환
  @Override
  public List<NFTListDTO> getNFTListByOwnerId(long ownerId) {
    try {
      User owner = User.builder().userId(ownerId).build();
      List<NFT> entityList = nftRepository.findByOwnerOrderByTransactionTimeDesc(owner);
      //엔티티를 DTO로 변환
      List<NFTListDTO> result = entityList.stream().map(m -> NFTListDTO.fromEntity(m)).collect(
          Collectors.toList());
      return result;
    }//try
    catch (Exception e) {
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
      User nowOwner = nftEntity.getOwner();
      String nowOwnerAddress = nowOwner.getAddress();
      User newOwner = User.builder().userId(nftTradeDTO.getBuyerId()).build();
      String newOwnerAddress = newOwner.getAddress();
      double price = nftTradeDTO.getTransactionPrice();
      String tokenUri = nftEntity.getTokenUri();
      //스마트 컨트랙트 호출해서
      //1. tokenUri에 해당되는 nft 소유자 newOwner로 변경해주고
      //2. newOwner의 잔액을 nowOwner의 잔액으로 변경시키는 트랜잭션 필요
      //trade(nowOwnerAddress, newOwnerAddress, tokenUri, price)
      nftEntity.updateIsOnSale(false);
      nftEntity.updateOwner(newOwner);
      nftEntity.updateTransactionTime(LocalDateTime.now());
      nftEntity.updateCurrentPrice(price);
      nftRepository.save(nftEntity);
    } catch (Exception e) {
      throw e;
    }//catch
  }

}
