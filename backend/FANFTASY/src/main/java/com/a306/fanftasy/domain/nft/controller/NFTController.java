package com.a306.fanftasy.domain.nft.controller;

import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTSourceTradeDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.nft.service.NFTService;
import com.a306.fanftasy.domain.nft.service.NFTSourceService;
import com.a306.fanftasy.domain.response.ResponseDefault;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/nft")
@Slf4j
public class NFTController {

    private final NFTService nftService;
    private final NFTSourceService nftSourceService;

    //1. NFT 생성
    @PostMapping
    public ResponseEntity<?> NFTAdd(@RequestParam("file") MultipartFile file, @RequestParam("info") NFTCreateDTO nftCreateDto){
        log.info("NFT 생성 요청 : " + nftCreateDto.toString());
        ResponseDefault responseDefault = null;
        try{
            nftService.addNFT(file, nftCreateDto);
            responseDefault = ResponseDefault.builder().success(true).messege("SUCCESS").build();
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error(e.getMessage());
            responseDefault = ResponseDefault.builder().success(false).messege("fail").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    //2. 마켓에서 리스트 반환
    @GetMapping("/market")
    public ResponseEntity<?> NFTMarketList(@RequestParam int orderType, @RequestParam int page,@RequestParam String keyword){
        log.info("NFT 마켓 페이지 리스트 요청");
        log.info("orderType : " + orderType + ", page : " + page + ", keyword : " + keyword);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                .messege("SUCCESS")
                .data(nftSourceService.getNFTSourceList(orderType, page, keyword))
                .success(true).build();
            log.info("NFT 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("NFT 조회 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    //3. 아티스트가 올린 NFT 컨텐츠 상세 페이지 반환
    @GetMapping("/market/{nftSourceId}")
    public ResponseEntity<?> NFTMardetDetail(@PathVariable long nftSourceId){
        log.info("NFT 마켓 페이지 상세 요청 : " + nftSourceId);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                .messege("SUCCESS")
                .data(nftSourceService.getNFTSourceDetail(nftSourceId))
                .success(true).build();
            log.info("NFT 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("NFT 조회 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }
    //4. 아티스트가 올린 NFT 최초 거래
    @PutMapping("/market")
    public ResponseEntity<?> NFTFirstTrade(@RequestBody NFTSourceTradeDTO nftSourceTradeDTO){
        log.info("NFT 거래 발생 : " + nftSourceTradeDTO.toString());
        ResponseDefault responseDefault = null;
        try{
            nftSourceService.modifyNFTSource(nftSourceTradeDTO);
            responseDefault = ResponseDefault.builder()
                .success(true)
                .messege("SUCCESS")
                .build();
            log.info("거래 반영 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("거래 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL")
                .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    //5. 아티스트 페이지에서 발매된 NFT목록 반환
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<?> NFTArtistList(@PathVariable long artistId){
        log.info("NFT 아티스트 페이지 목록 요청 : " + artistId);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                .messege("SUCCESS")
                .data(nftSourceService.getNFTSourceListByArtist(artistId))
                .success(true).build();
            log.info("NFT 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("NFT 조회 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    //6. 회원 소유 NFT목록 반환
    @GetMapping("/user/{ownerId}")
    public ResponseEntity<?> NFTUserList(@PathVariable long ownerId){
        log.info("NFT 개인 페이지 목록 요청 : " + ownerId);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                .messege("SUCCESS")
                .data(nftService.getNFTListByOwnerId(ownerId))
                .success(true).build();
            log.info("NFT 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("NFT 조회 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    //7. 개인이 보유한 NFT 상세
    @GetMapping("/{nftId}")
    public ResponseEntity<?> NFTDetail(@PathVariable long nftId){
        log.info("NFT 조회 요청 : " + nftId);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                .messege("SUCCESS")
                .data(nftService.getNFT(nftId))
                .success(true).build();
            log.info("NFT 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("NFT 조회 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }


    //8. NFT 개인 거래
    @PutMapping
    public ResponseEntity<?> NFTTrade(@RequestBody NFTTradeDTO nftTradeDTO){
        log.info("NFT 거래 발생 : " + nftTradeDTO.toString());
        ResponseDefault responseDefault = null;
        try{
            nftService.modifyNFT(nftTradeDTO);
            responseDefault = ResponseDefault.builder()
                .success(true)
                .messege("SUCCESS")
                .build();
            log.info("거래 반영 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("거래 실패");
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL")
                .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }
}
