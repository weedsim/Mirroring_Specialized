package com.a306.fanftasy.domain.nft.repository;

import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTRepository extends JpaRepository<NFT, Long> {
  NFT findById(long nftId);


  List<NFT> findByRegArtist(User regArtist);

}
