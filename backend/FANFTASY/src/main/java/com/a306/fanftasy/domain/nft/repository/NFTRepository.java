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

//  @Query("SELECT n1 FROM NFT n1 WHERE n1.isOnSale = true "
//          + "AND n1.nftSource.title like %:keyword% or n1.nftSource.content like %:keyword% or n1.nftSource.regArtist.nickname like %:keyword%"
//          + "AND n1.currentPrice = (SELECT MIN(n2.currentPrice) FROM NFT n2 "
//          + "WHERE n2.nftSource = n1.nftSource AND n2.isOnSale = true) "
//          + "AND n1.transactionTime = (SELECT MAX(n3.transactionTime) FROM NFT n3 "
//          + "WHERE n3.nftSource = n1.nftSource AND n3.currentPrice = n1.currentPrice AND n3.isOnSale = true) "
//          + "AND n1.nftId = (SELECT MIN(n4.nftId) FROM NFT n4 "
//          + "WHERE n4.nftSource = n1.nftSource AND n4.currentPrice = n1.currentPrice "
//          + "AND n4.transactionTime = n1.transactionTime AND n4.isOnSale = true)")
//  List<NFT> findByOnSaleOrderByPriceByCondition(@Param("keyword") String keyword);

  /**
   * 판매중인 NFT 중 최신순 현재 판매 중인 가장 작은 값 찾기
   */
  // 판매중인 NFTSourceId 찾기
  @Query("select n.nftSource.nftSourceId from NFT n where n.isOnSale = true group by n.nftSource")
  List<Long> findNftSourceIdIsOnSale();
  // 판매중인 NFTSourceId 중 검색
  @Query("SELECT n FROM NFTSource n WHERE n.nftSourceId IN :nftSourceIdIsOnSale AND (n.content like %:keyword% or n.title like %:keyword% or n.regArtist.nickname like %:keyword%) order by n.regDate DESC")
  List<NFTSource> findNFTMarketListOrderByRegDate(@Param("nftSourceIdIsOnSale") List<Long> nftSourceIdIsOnSale, @Param("keyword") String keyword);
  // 판매 값 중 가장 낮은 값 찾기
  @Query("select min(n.currentPrice) from NFT n where n.isOnSale = true and n.nftSource.nftSourceId = :nftSourceId")
  Double findMinCurrentPrice(@Param("nftSourceId") Long nftSourceId);
}
