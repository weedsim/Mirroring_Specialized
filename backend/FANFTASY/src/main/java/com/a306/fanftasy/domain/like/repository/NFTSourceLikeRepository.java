package com.a306.fanftasy.domain.like.repository;

import com.a306.fanftasy.domain.like.entity.NFTSourceLike;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTSourceLikeRepository extends JpaRepository<NFTSourceLike, Long> {
  NFTSourceLike findByNftSourceAndUser(NFTSource nftSource, User user );
  void deleteByNftSourceAndUser(NFTSource nftSource, User user);
}
