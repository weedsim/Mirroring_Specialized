package com.a306.fanftasy.domain.nft;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@ToString
@Builder
public class NFT {

    @Id
    @Column(name = "nft_id")
    long nftId;

    @Column(name = "token_uri")
    String tokenUri;

}
