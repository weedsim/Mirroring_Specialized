package com.a306.fanftasy.domain.nft.controller;

import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import com.a306.fanftasy.domain.nft.service.NFTService;
import com.a306.fanftasy.domain.nft.service.NFTServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/nft")
@Slf4j
public class NFTController {

    NFTService nftService;

    @PostMapping
    public ResponseEntity<?> NFTAdd(NFTCreateDTO nftCreateDto){
        log.info("NFT 생성 요청 : " + nftCreateDto.toString());
        try{
            nftService.addNFT(nftCreateDto);
            return ResponseEntity.ok().body("SUCCESS");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{nftId}")
    public ResponseEntity<?> NFTDetail(@PathVariable int nftId){
        log.info("NFT 조회 요청 : " + nftId);
        try{
            return ResponseEntity.ok().body(nftService.getNFT(nftId));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> NFTTrade(NFTTradeDTO nftTradeDTO){
        log.info("NFT 거래 발생 : " + nftTradeDTO.toString());
        try{
            nftService.modifyNFT(nftTradeDTO);
            return ResponseEntity.ok().body();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/list")
    public ResponseEntity<?> NFTDetail(@RequestParam int regArtistId, int ownerId, String keyword){
        log.info("NFT 리스트 조회 요청 : " + "아티스트-"+regArtistId+", 소유자"+ownerId+", 검색어"+keyword);
        try{
            return ResponseEntity.ok().body(nftService.getNFTList(regArtistId, ownerId, keyword));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
