package com.a306.fanftasy.domain.like.repository;

import com.a306.fanftasy.domain.like.entity.NFTLike;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTLikeRepository extends JpaRepository<NFTLike, Long> {

//  @Query("SELECT n FROM NFT n WHERE n.nftSource = :nftSource And n.nftSource.regArtist = n.owner")
  NFTLike findFirstByNftAndUser(NFT nft, User user );
}
