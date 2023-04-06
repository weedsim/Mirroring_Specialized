package com.a306.fanftasy.domain.nft.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
  @Value("${blockchain.NFTContractAddress}")
  private String NFT_CONTRACT_ADDRESS;
  @Value("${blockchain.SaleFactoryAddress}")
  private String SALE_FACTORY_ADDRESS;

  public BasicService(EthereumService ethereumService)
  {
    this.ethereumService = ethereumService;
  }

  public long create(String to, String tokenURI) throws IOException, ExecutionException, InterruptedException {
    try{
    log.info("create 메소드 호출");
      Function function = new Function(
          "create",
          Arrays.asList(new Address(to),new Utf8String(tokenURI), new Address(SALE_FACTORY_ADDRESS) ),
          Arrays.asList(new TypeReference<Uint256>() {
          })
      );
    return ethereumService.createNFT(function, NFT_CONTRACT_ADDRESS);
    }catch(Exception e){
      throw e;
    }
    // 2. ethereum을 function 변수로 통해 호출
  }//create
  public String createSale(long tokenId, double originPrice)
      throws IOException, ExecutionException, InterruptedException {
    try{
      log.info("sale 메소드 호출");
      Double weiD = originPrice * Math.pow(10, 18);
      log.info("weiD");
      BigInteger wei = BigDecimal.valueOf(weiD).toBigInteger();
      Function function = new Function(
          "createSale",
          Arrays.asList(new Uint256(tokenId),new Uint256(wei) ),
          Arrays.asList(new TypeReference<Address>() {
          })
      );
      return ethereumService.createSale(function, SALE_FACTORY_ADDRESS);
    }catch(Exception e){
      throw e;
    }
  }

  public void getNumber(int num)
  {
  }

  public String getOwner(long nftId) throws IOException, ExecutionException, InterruptedException {
    try{
      log.info("getOwner 메소드 호출");
      Function function = new Function(
          "ownerOf",
          Arrays.asList(new Uint256(nftId)),
          Arrays.asList(new TypeReference<Address>() {
          })
      );
      return ethereumService.ethCall(function);
    }catch(Exception e){
      throw e;
    }
    // 2. ethereum을 function 변수로 통해 호출
  }

}