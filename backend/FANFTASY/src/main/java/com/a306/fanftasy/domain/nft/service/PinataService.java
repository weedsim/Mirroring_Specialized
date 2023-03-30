package com.a306.fanftasy.domain.nft.service;

import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import pinata.PinataException;

public interface PinataService {
String fileToPinata(MultipartFile file) throws PinataException, IOException;
String jsonToPinata(String info, String fileCID) throws IOException, PinataException;
}
