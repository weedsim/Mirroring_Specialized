package com.a306.fanftasy.domain.nft.exception;

import lombok.Getter;

@Getter
public class NFTCreateException extends RuntimeException{
  public NFTCreateException(String message){
    super(message);
  }
}
