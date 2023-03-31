package com.a306.fanftasy.domain.like.controller;

import com.a306.fanftasy.domain.like.service.LikeService;
import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.response.ResponseDefault;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/like")
public class LikeController {

  private final LikeService likeService;
  @GetMapping("/source")
  public ResponseEntity<?> sourceLike(@RequestParam long nftSourceId, @RequestParam long userId){
    log.info("NFT Source 좋아요 요청 : " + nftSourceId);
    ResponseDefault responseDefault = null;
    try{
      likeService.sourceLike(nftSourceId, userId);
      responseDefault = ResponseDefault.builder().success(true).messege("SUCCESS").build();
      return ResponseEntity.ok().body(responseDefault);
    }catch (Exception e){
      log.error(e.getMessage());
      responseDefault = ResponseDefault.builder().success(false).messege("fail").build();
      return ResponseEntity.badRequest().body(responseDefault);
    }
  }
  @GetMapping("/source-cancel")
  public ResponseEntity<?> sourceCancel(@RequestParam long nftSourceId, @RequestParam long userId){
    log.info("NFT Source 좋아요 취소 : " + nftSourceId);
    ResponseDefault responseDefault = null;
    try{
      likeService.sourceCancel(nftSourceId, userId);
      responseDefault = ResponseDefault.builder().success(true).messege("SUCCESS").build();
      return ResponseEntity.ok().body(responseDefault);
    }catch (Exception e){
      log.error(e.getMessage());
      responseDefault = ResponseDefault.builder().success(false).messege("fail").build();
      return ResponseEntity.badRequest().body(responseDefault);
    }
  }
  @GetMapping("/nft")
  public ResponseEntity<?> nftLike(@RequestParam long nftId, @RequestParam long userId){
    log.info("NFT 좋아요 요청 : " + nftId);
    ResponseDefault responseDefault = null;
    try{
      likeService.nftLike(nftId, userId);
      responseDefault = ResponseDefault.builder().success(true).messege("SUCCESS").build();
      return ResponseEntity.ok().body(responseDefault);
    }catch (Exception e){
      log.error(e.getMessage());
      responseDefault = ResponseDefault.builder().success(false).messege("fail").build();
      return ResponseEntity.badRequest().body(responseDefault);
    }
  }
  @GetMapping("/nft-cancel")
  public ResponseEntity<?> nftCancel(@RequestParam long nftId, @RequestParam long userId){
    log.info("NFT 좋아요 취소 : " + nftId);
    ResponseDefault responseDefault = null;
    try{
      likeService.nftCancel(nftId, userId);
      responseDefault = ResponseDefault.builder().success(true).messege("SUCCESS").build();
      return ResponseEntity.ok().body(responseDefault);
    }catch (Exception e){
      log.error(e.getMessage());
      responseDefault = ResponseDefault.builder().success(false).messege("fail").build();
      return ResponseEntity.badRequest().body(responseDefault);
    }
  }
}
