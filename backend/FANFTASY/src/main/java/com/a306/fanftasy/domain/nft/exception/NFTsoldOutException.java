package com.a306.fanftasy.domain.nft.exception;

import lombok.Getter;

@Getter
public class NFTsoldOutException extends RuntimeException{
  public NFTsoldOutException(String message){
    super(message);
  }
}
