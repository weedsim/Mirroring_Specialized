package com.a306.fanftasy.domain.like.service;

public interface LikeService {
  void sourceLike(long nftSourceId, long userId);
  void sourceCancel(long nftSourceId, long userId);
  void nftLike(long nftId, long userId);
  void nftCancel(long nftId, long userId);
}
