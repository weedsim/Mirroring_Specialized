package com.a306.fanftasy.domain.like.entity;

import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
@Builder
public class NFTSourceLike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "nft_source_like_id")
  private Long nftSourceLikeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nft_source_id")
  private NFTSource nftSource;

}