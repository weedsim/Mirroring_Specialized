package com.a306.fanftasy.domain.nft.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
@Slf4j
public class BasicService{

  private EthereumService ethereumService;

  public BasicService(EthereumService ethereumService)
  {
    this.ethereumService = ethereumService;
  }

  public long create(String to, String tokenURI) throws IOException, ExecutionException, InterruptedException {
    try{
    log.info("create 메소드 호출");
      Function function = new Function(
          "create",
          Arrays.asList(new Address(to),new Utf8String(tokenURI) ),
          Arrays.asList(new TypeReference<Uint256>() {
          })
      );
    return ethereumService.transaction(function);

    }catch(Exception e){
      throw e;
    }
    // 2. ethereum을 function 변수로 통해 호출
  }

  public void getNumber(int num)
  {
    // this method implement next posting
  }

  public void getOwner()
  {
    // this method implement next posting
  }

}