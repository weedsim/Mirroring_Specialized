package com.a306.fanftasy.domain.nft.entity;


import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@Builder
public class NFTSource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "nft_source_id")
  private Long nftSourceId;
  @Column(name = "title")
  private String title; //제목
  private String content; //내용
  @Column(name = "total_num")
  private long totalNum; //발행량
  @Column(name = "origin_price")
  private double originPrice; //발행가
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reg_artist")
  private User regArtist; //등록 아티스트
  @Column(name = "file_CID")
  private String fileCID; //파일 uri

  @Column(name = "meta_CID")
  private String metaCID;
  @Column(name = "file_type")
  private String fileType; //파일 종류
  @Column(name = "reg_date")
  private LocalDateTime regDate; //등록시간
  @Column(name = "remain_num")
  private long remainNum; //잔여량
  @Column(name = "like_num")
  private long likeNum;//좋아요수
  public void updateRemainNum(long remainNum) {
    this.remainNum = remainNum;
  }
  public void updateLikeNum(long likeNum) {this.likeNum =  likeNum;}
}
