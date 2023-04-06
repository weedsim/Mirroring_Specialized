package com.a306.fanftasy.domain.nft.service;

import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;

import com.a306.fanftasy.domain.nft.exception.NFTCreateException;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pinata.Pinata;
import pinata.PinataException;
import pinata.PinataResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class PinataServiceImpl implements PinataService{

  @Value("${pinata.API_Key}")
  private String API_KEY;
  @Value("${pinata.API_Secret}")
  private String API_Secret;
  @Value("${pinata.JWT}")
  private String JWT;


  @Override
  public String fileToPinata(MultipartFile file) throws PinataException, IOException {
    try{
      log.info("fileToPinata : " + file.getOriginalFilename());
      Pinata pinata = new Pinata(API_KEY, API_Secret);
      File conFile = new File(file.getOriginalFilename());
      conFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(conFile);
      fos.write(file.getBytes());
      fos.close();
      PinataResponse pinataResponse = pinata.pinFileToIpfs(conFile);
      log.info(pinataResponse.getBody());
      String fileCID = "https://ipfs.io/ipfs/"+cidFromResponse(pinataResponse.getBody());
      return fileCID;
    }catch(PinataException e){
      throw e;
    }catch(IOException e){
      throw e;
    }
  }

  @Override
  public NFTCreateDTO jsonToPinata(String info, String fileCID)
      throws IOException, PinataException {
    log.info("metadata pinata 저장 시작, info : " + info);
    Pinata pinata = new Pinata(API_KEY, API_Secret);
    //String input을 json타입으로 변환
    ObjectMapper mapper = new ObjectMapper();
    JSONObject jsonObject = mapper.convertValue(info, JSONObject.class);
    jsonObject.put("regDate", LocalDateTime.now());
    jsonObject.put("fileCID", fileCID);
    log.info("변환된 json : "+ jsonObject);
    PinataResponse pinataResponse = pinata.pinJsonToIpfs(jsonObject);
    log.info(pinataResponse.getBody());
    String metaCID = "https://ipfs.io/ipfs/"+cidFromResponse(pinataResponse.getBody());
    jsonObject.put("metaCID", metaCID);

    //jsonObject를 DTO로 반환
    mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    mapper.registerModule(new JavaTimeModule());
    NFTCreateDTO nftCreateDTO = mapper.readValue(jsonObject.toString(), NFTCreateDTO.class);
    if(nftCreateDTO.getTotalNum()<=0||nftCreateDTO.getOriginPrice()<0||nftCreateDTO.getRegDate().isBefore(LocalDateTime.now())){
      throw new NFTCreateException("잘못된 입력값입니다.");
    }
    log.info("변환된 dto : "+ nftCreateDTO);
    return nftCreateDTO;
  }

  public String cidFromResponse(String json) throws JsonProcessingException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, String> map = mapper.readValue(json, Map.class);
      return map.get("IpfsHash");
    } catch (Exception e) {
      throw e;
    }
  }
}
