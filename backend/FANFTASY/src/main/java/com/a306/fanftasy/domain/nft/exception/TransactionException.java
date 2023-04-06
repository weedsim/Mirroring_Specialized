package com.a306.fanftasy.domain.nft.exception;

import lombok.Getter;

@Getter
public class TransactionException extends RuntimeException{
  public TransactionException(String message){
    super(message);
  }
}
