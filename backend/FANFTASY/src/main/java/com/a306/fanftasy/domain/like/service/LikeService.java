package com.a306.fanftasy.domain.like.service;

public interface LikeService {
  void sourceLike(long nftSourceId);
  void sourceCancel(long nftSourceId);
  void nftLike(long nftId);
  void nftCancel(long nftId);
}
