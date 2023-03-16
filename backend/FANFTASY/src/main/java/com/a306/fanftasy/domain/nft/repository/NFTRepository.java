package com.a306.fanftasy.domain.nft.repository;

import com.a306.fanftasy.domain.nft.NFT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTRepository extends JpaRepository<NFT, Long> {
    void save()
}
