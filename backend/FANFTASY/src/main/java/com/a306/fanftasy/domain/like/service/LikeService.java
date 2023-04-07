package com.a306.fanftasy.domain.like.service;

import com.a306.fanftasy.domain.nft.dto.NFTListDTO;
import com.a306.fanftasy.domain.nft.dto.NFTSourceListDTO;

import java.util.List;

public interface LikeService {
  void sourceLike(long nftSourceId, long userId);
  void sourceCancel(long nftSourceId, long userId);
  void nftLike(long nftId, long userId);
  void nftCancel(long nftId, long userId);

    List<NFTSourceListDTO> nftSourceLikeList(long userId);

  List<NFTListDTO> nftLikeList(long userId);
}
