package com.a306.fanftasy.domain.nft.service;

import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.PinataJsonDTO;
import com.a306.fanftasy.domain.nft.dto.PinataResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
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
  private static String API_KEY;
  @Value("${pinata.API_Secret}")
  private static String API_Secret;
  @Value("${pinata.JWT}")
  private static String JWT;

  private Pinata pinata = new Pinata("24758d33d2d03de6448c", "059c98642c4243319121d7658e30ff7e3685951b2f27de88e09046e30eedd18c");
  @Override
  public String fileToPinata(MultipartFile file) throws PinataException, IOException {
    try{
      log.info(API_KEY);
      File conFile = new File(file.getOriginalFilename());
      conFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(conFile);
      fos.write(file.getBytes());
      fos.close();
      PinataResponse pinataResponse = pinata.pinFileToIpfs(conFile);
      log.info(pinataResponse.getBody());
      String fileCID = cidFromResponse(pinataResponse.getBody());
      return fileCID;
    }catch(PinataException e){
      throw e;
    }catch(IOException e){
      throw e;
    }
  }

  @Override
  public String jsonToPinata(String info, String fileCID)
      throws IOException, PinataException {
    log.info("metadata pinata 저장 시작");
    //String input을 json타입으로 변환
    ObjectMapper mapper = new ObjectMapper();
    JSONObject jsonObject = new JSONObject();
    NFTCreateDTO nftCreateDTO = mapper.readValue(info, NFTCreateDTO.class);
    jsonObject.put("title", nftCreateDTO.getTitle() );
    jsonObject.put("content", nftCreateDTO.getContent());
    jsonObject.put("totalNum", nftCreateDTO.getTotalNum());
    jsonObject.put("originPrice", nftCreateDTO.getOriginPrice());
    jsonObject.put("regArtistId", nftCreateDTO.getRegArtistId());
    jsonObject.put("fileType",nftCreateDTO.getFileType());
    jsonObject.put("regDate", LocalDateTime.now());
    jsonObject.put("fileCID", fileCID);
    log.info("변환된 json : "+ jsonObject);
    PinataResponse pinataResponse = pinata.pinJsonToIpfs(jsonObject);
    log.info(pinataResponse.getBody());
    String metaCID = cidFromResponse(pinataResponse.getBody());
    return metaCID;
  }



  public String cidFromResponse(String json) throws JsonProcessingException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, String> map = mapper.readValue(json, Map.class);
//      PinataResponseDTO dto = mapper.readValue(json, PinataResponseDTO.class);
//      log.info(dto.toString());
//      log.info(dto.getIpfsHash());
      return map.get("IpfsHash");
    } catch (Exception e) {
      throw e;
    }
  }
}
