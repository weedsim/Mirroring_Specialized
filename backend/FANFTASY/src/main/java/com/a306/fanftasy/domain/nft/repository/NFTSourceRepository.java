package com.a306.fanftasy.domain.nft.repository;

import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NFTSourceRepository extends JpaRepository<NFTSource, Long> {
  NFTSource findById(long nftSourceId);

  //keyword로 찾기
  @Query("SELECT n FROM NFTSource n WHERE (:keyword IS NULL OR n.regArtist.nickname LIKE %:keyword% OR n.title LIKE %:keyword% OR n.content LIKE %:keyword%) And n.remainNum >0")
  List<NFTSource> findByCondition(@Param("keyword") String keyword, Pageable pageable);
  List<NFTSource> findByRegArtistOrderByRegDateDesc(User regArtist);
}
