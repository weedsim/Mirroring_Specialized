package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.RequestModifyPurchaseBoard;
import com.a306.fanftasy.domain.board.dto.RequestPurchaseBoard;
import com.a306.fanftasy.domain.board.dto.RequestSalesBoard;
import com.a306.fanftasy.domain.board.dto.ResponsePurchaseBoard;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.nft.NFT;
import com.a306.fanftasy.domain.user.User;

public interface BoardService {

    User findUserById(Long id);
    void savePurchaseBoard(RequestPurchaseBoard requestPurchaseBoard, User user);
    ResponsePurchaseBoard findPurchaseBoardById(Long id);
    void updateBoardViews(Long id, int views);
    Board findBoardById(Long id);
    void removePurchaseBoard(Long id);
    void modifyPurchaseBoard(Long id, RequestModifyPurchaseBoard requestModifyPurchaseBoard);


    NFT findNFTById(Long nftId);
    void saveSalesBoard(RequestSalesBoard requestSalesBoard, User user);

}
