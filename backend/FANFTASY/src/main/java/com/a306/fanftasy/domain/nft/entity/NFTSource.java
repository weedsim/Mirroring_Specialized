package com.a306.fanftasy.domain.nft.entity;


import com.a306.fanftasy.domain.user.entity.User;
import javax.validation.constraints.NotNull;
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
  @NotNull
  private String title; //제목
  @NotNull
  private String content; //내용
  @Column(name = "total_num")
  @NotNull
  private long totalNum; //발행량
  @Column(name = "origin_price")
  @NotNull
  private double originPrice; //발행가
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reg_artist")
  @NotNull
  private User regArtist; //등록 아티스트
  @Column(name = "file_CID")
  @NotNull
  private String fileCID; //파일 uri

  @Column(name = "meta_CID")
  @NotNull
  private String metaCID;
  @Column(name = "file_type")
  @NotNull
  private String fileType; //파일 종류
  @Column(name = "reg_date")
  @NotNull
  private LocalDateTime regDate; //등록시간
  @Column(name = "end_date")
  @NotNull
  private LocalDateTime endDate; //마감시간
  @Column(name = "remain_num")
  @NotNull
  private long remainNum; //잔여량
  @Column(name = "like_num")
  @NotNull
  private long likeNum;//좋아요수
  public void updateRemainNum(long remainNum) {
    this.remainNum = remainNum;
  }
  public void updateTotalNum(long totalNum) {
    this.totalNum = totalNum;
  }
  public void updateLikeNum(long likeNum) {this.likeNum =  likeNum;}
}
