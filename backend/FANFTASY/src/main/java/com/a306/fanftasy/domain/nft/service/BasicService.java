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

  public String minting(String to, String tokenURI) throws IOException, ExecutionException, InterruptedException {
    log.info("create 메소드 호출");
//    List<Type> inputParameters = new ArrayList<>();
//    inputParameters.add(new Address(to));
////    inputParameters.add(new Utf8String(tokenURI));
//    log.info("input생성완료");
//    List<TypeReference<?>> outputParameters = new ArrayList<>();
//    outputParameters.add(new TypeReference<Uint256>() {});
//    Arrays.asList(new TypeReference<Uint256>() {});
    // 1. 호출하고자 하는 function 세팅[functionName, parameters]
    Function function = new Function("minting",
        Arrays.asList(new Address(to), new Utf8String(tokenURI)),
        Arrays.asList(new TypeReference<Uint256>() {}));
    // 2. ethereum을 function 변수로 통해 호출
    return ethereumService.ethSendTransaction(function);
  }

  public void getNumber(int num)
  {
    // this method implement next posting
  }

  public void getOwner()
  {
    // this method implement next posting
  }

  public void setPot(int num) throws IOException, ExecutionException, InterruptedException {
    // 1. 호출하고자 하는 function 세팅[functionName, parameters]
    Function function = new Function("setPot",
        Arrays.asList(new Uint256(num)),
        Collections.emptyList());

    // 2. sendTransaction
    String txHash = ethereumService.ethSendTransaction(function);

    // 3. getReceipt
    TransactionReceipt receipt = ethereumService.getReceipt(txHash);
    System.out.println("receipt = " + receipt);
  }


}