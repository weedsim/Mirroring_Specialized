package com.a306.fanftasy.domain.like.repository;

import com.a306.fanftasy.domain.like.entity.NFTSourceLike;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NFTSourceLikeRepository extends JpaRepository<NFTSourceLike, Long> {
  NFTSourceLike findByNftSourceAndUser(NFTSource nftSource, User user );
  void deleteByNftSourceAndUser(NFTSource nftSource, User user);

  @Query("select n from NFTSource n where n.nftSourceId in (select n1.nftSource from NFTSourceLike n1 where n1.user.userId = :userId) order by n.regDate DESC")
  List<NFTSource> findByNftSourceLikeUserOrderByRegDateDesc(@Param("userId") Long userId);
}
