package com.a306.fanftasy.domain.like.repository;

import com.a306.fanftasy.domain.like.entity.NFTLike;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NFTLikeRepository extends JpaRepository<NFTLike, Long> {

//  @Query("SELECT n FROM NFT n WHERE n.nftSource = :nftSource And n.nftSource.regArtist = n.owner")
  NFTLike findByNftAndUser(NFT nft, User user );

  void deleteByNftAndUser(NFT nft, User user);

  @Query("select n from NFT n where n.nftId in (select n1.nft.nftId from NFTLike n1 where n1.user.userId =:userId) order by n.transactionTime DESC")
  List<NFT> findByNftLikeUserOrderByRegDateDesc(@Param("userId") long userId);
}
