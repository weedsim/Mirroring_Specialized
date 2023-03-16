package com.a306.fanftasy.domain.board.controller;

import com.a306.fanftasy.domain.board.dto.RequestPurchaseBoard;
import com.a306.fanftasy.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseBoardSave(@RequestBody RequestPurchaseBoard purchaseBoard) {
        System.out.println(purchaseBoard);

//        try {
//            boardService.savePurchaseBoard(purchaseBoard);
//            return new ResponseEntity<>("구매글 등록 성공", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("구매글 등록 실패", HttpStatus.NO_CONTENT);
//        }
        return null;
    }

}
