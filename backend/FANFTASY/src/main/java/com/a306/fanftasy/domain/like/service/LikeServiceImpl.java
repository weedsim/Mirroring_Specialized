package com.a306.fanftasy.domain.like.service;

import com.a306.fanftasy.domain.like.repository.NFTLikeRepository;
import com.a306.fanftasy.domain.like.repository.NFTSourceLikeRepository;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.nft.repository.NFTSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements  LikeService{

  private final NFTLikeRepository nftLikeRepository;
  private final NFTSourceLikeRepository nftSourceLikeRepository;
  private final NFTRepository nftRepository;
  private final NFTSourceRepository nftSourceRepository;

  @Override
  public void sourceLike(long nftSourceId) {
    //user와 nftsourceId를 통해서 sourcelikeDB에 추가한 후,



    //sourcelike에 좋아요 수를 늘려주기


  }

  @Override
  public void sourceCancel(long nftSourceId) {
    //user와 nftsourceId를 통해서 sourcelikeDB에서 삭제한 후,
    //sourcelike에 좋아요 수 내리기
  }

  @Override
  public void nftLike(long nftId) {
    //user와 nftsourceId를 통해서 nftlikeDB에 추가한 후,
    //nftlike에 좋아요 수 늘리기
  }

  @Override
  public void nftCancel(long nftId) {
    //user와 nftsourceId를 통해서 nftlikeDB에서 삭제한 후,
    //nftlike에 좋아요 수 내리기
  }
}
