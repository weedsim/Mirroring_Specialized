//package com.a306.fanftasy.domain.nft.entity;
//
//
//import com.a306.fanftasy.domain.user.entity.User;
//import java.time.LocalDateTime;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@DynamicInsert
//@DynamicUpdate
//@Getter
//@ToString
//@Builder
//public class NFTSourceLike {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "nft_source_like_id")
//  private Long nftSourceLikeId;
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id")
//  private User user;
//
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "nft_source_id")
//  private NFTSource nftSource;
//
//
//}
