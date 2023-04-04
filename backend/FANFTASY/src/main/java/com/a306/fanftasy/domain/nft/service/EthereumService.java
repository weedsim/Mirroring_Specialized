package com.a306.fanftasy.domain.nft.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

@Slf4j
@RequiredArgsConstructor
@Service
public class EthereumService {

  @Value("${blockchain.ContractAddress}")
  private String CONTRACT_ADDRESS;
  @Value("${blockchain.AdminPrivateKey}")
  private String ADMIN_PRIVATE_KEY;
  @Value("${blockchain.AdminAddress}")
  private String ADMIN_ADDRESS;
  @Value("${blockchain.NetworkURL}")
  private String NETWORK_URL;
  @Value("${blockchain.NetworkID}")
  private int NETWORK_ID;
  private BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
  private BigInteger GAS_PRICE = BigInteger.valueOf(0);


//  public Object ethCall(Function function)
//      throws IOException, ExecutionException, InterruptedException {
//    Admin web3j = Admin.build(new HttpService(NETWORK_URL));
//
//    //AccontLock해제
//    PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(ADMIN_ADDRESS,
//        ADMIN_PRIVATE_KEY).send();
//    log.info(personalUnlockAccount.toString());
//    if (personalUnlockAccount.accountUnlocked()) { // unlock 일때
//
//      //4. account에 대한 nonce값 가져오기.
//      EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
//          ADMIN_ADDRESS, DefaultBlockParameterName.LATEST).sendAsync().get();
//
//      BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//      log.info("nonce : " + nonce);
//      log.info(FunctionEncoder.encode(function));
//      //5. Transaction값 제작
//      Transaction transaction = Transaction.createFunctionCallTransaction(ADMIN_ADDRESS, nonce,
//          Transaction.DEFAULT_GAS,
//          BigInteger.valueOf(27000), CONTRACT_ADDRESS,
//          FunctionEncoder.encode(function));
//      log.info("transaction 전송");
//      // 6. ethereum Call
//      EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).send();
//      log.info("transaction 전송");
//      // transaction에 대한 transaction Hash값 얻기.
//      String transactionHash = ethSendTransaction.getTransactionHash();
//      log.info("transaction 해쉬 : " + transactionHash);
//      // ledger에 쓰여지기 까지 기다리기.
//      Thread.sleep(5000);
//
//      return transactionHash;
//    } else {
//    }
//  }//ethcall

  public long transaction(Function function)
      throws IOException, InterruptedException, ExecutionException {
    log.info("ethSendTransaction 호출 : " + function.getInputParameters().toString());
    Web3j web3j = Web3j.build(new HttpService(NETWORK_URL));
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    log.info("네트워크 버전 : " + web3ClientVersion.getWeb3ClientVersion());
    //2.
    Credentials credentials = getCredentialsFromPrivateKey();
    //3.
    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
        credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
    //4.
    RawTransaction rawTransaction = RawTransaction.createTransaction(
        nonce, GAS_PRICE, GAS_LIMIT, CONTRACT_ADDRESS, FunctionEncoder.encode(function));
    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
    String hexValue = Numeric.toHexString(signedMessage);
    EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
    String transactionHash = ethSendTransaction.getTransactionHash();
    log.info("transactionHash : " + transactionHash);
    List<Type> decode = FunctionReturnDecoder.decode(ethSendTransaction.getResult(),
        function.getOutputParameters());
    log.info("hash value : " + decode.get(0).getValue());
    Thread.sleep(5000);
    EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();
    log.info("hash value : " + transactionReceipt.getResult());
    long tokenID = Long.decode(transactionReceipt.getResult().getLogs().get(0).getTopics().get(3));
    log.info("token생성 ID : " + tokenID);
    return tokenID;
  }//ethSendTransaction

  public TransactionReceipt getReceipt(String transactionHash) throws IOException {
    Admin web3j = Admin.build(new HttpService(NETWORK_URL));
    EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash)
        .send();

    if (transactionReceipt.getTransactionReceipt().isPresent()) {
      System.out.println("transactionReceipt.getResult().getContractAddress() = " +
          transactionReceipt.getResult());
    } else {
      System.out.println("transaction complete not yet");
    }

    return transactionReceipt.getResult();
  }

  private Credentials getCredentialsFromPrivateKey() {
    return Credentials.create(ADMIN_PRIVATE_KEY);
  }
}//class
