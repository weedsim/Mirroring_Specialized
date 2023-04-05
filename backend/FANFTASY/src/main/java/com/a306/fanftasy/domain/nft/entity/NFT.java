package com.a306.fanftasy.domain.nft.entity;


import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
@Builder
public class NFT {

  @Id
  @Column(name = "nft_id")
  private Long nftId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id")
  private User owner;

  @Column(name = "sale_contract")
  private String saleContract;

  @Column(name = "is_on_sale")
  private Boolean isOnSale;

  @Column(name = "current_price")
  private double currentPrice;

  @Column(name = "transaction_time")
  private LocalDateTime transactionTime;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nft_source_id")
  private NFTSource nftSource;

  @Column(name = "edition_num")
  private long editionNum;

  @Column(name = "nft_like_num")
  private long nftLikeNum;

  public void updateOwner(User owner) {
    this.owner = owner;
  }

  public void updateIsOnSale(boolean isOnSale) {
    this.isOnSale = isOnSale;
  }

  public void updateCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }

  public void updateTransactionTime(LocalDateTime transactionTime) {
    this.transactionTime = transactionTime;
  }

  public void updateNFTLikeNum(long nftLikeNum){ this.nftLikeNum = nftLikeNum;}
}
