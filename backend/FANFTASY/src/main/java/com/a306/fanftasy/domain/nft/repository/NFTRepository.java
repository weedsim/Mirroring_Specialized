package com.a306.fanftasy.domain.nft.repository;

import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NFTRepository extends JpaRepository<NFT, Long> {
  NFT findById(long nftId);

//  @Query("SELECT n FROM NFT n WHERE n.nftSource = :nftSource And n.nftSource.regArtist = n.owner")
  NFT findFirstByNftSourceAndOwner(NFTSource nftSource, User regArtist );

  List<NFT> findByOwnerOrderByTransactionTimeDesc(User owenr);

}
