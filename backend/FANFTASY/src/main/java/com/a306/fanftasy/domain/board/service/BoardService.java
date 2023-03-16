package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.RequestPurchaseBoard;
import com.a306.fanftasy.domain.board.dto.ResponsePurchaseBoard;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.user.User;

public interface BoardService {

    User findUserById(Long id);
    void savePurchaseBoard(RequestPurchaseBoard requestPurchaseBoard, User user);
    ResponsePurchaseBoard findPurchaseBoardById(Long id);
    Board findBoardById(Long id);
    void removePurchaseBoard(Long id);
}
