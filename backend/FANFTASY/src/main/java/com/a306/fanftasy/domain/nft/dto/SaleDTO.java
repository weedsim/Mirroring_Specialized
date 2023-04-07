package com.a306.fanftasy.domain.nft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
  long nftId;
  String contractAddress;
  double price;
}
