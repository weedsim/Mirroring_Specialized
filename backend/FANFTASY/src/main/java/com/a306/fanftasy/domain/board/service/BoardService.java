package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.*;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.domain.Page;

public interface BoardService {

    User findUserById(Long id);
    void savePurchaseBoard(RequestPurchaseBoard requestPurchaseBoard, User user);
    ResponsePurchaseBoard findPurchaseBoardById(Long id);
    void updateBoardViews(Long id, int views);
    Board findBoardById(Long id);
    void removeBoard(Long id);
    void modifyPurchaseBoard(Long id, RequestModifyPurchaseBoard requestModifyPurchaseBoard);


    NFT findNFTById(Long nftId);
    void saveSalesBoard(RequestSalesBoard requestSalesBoard, User user, NFT nft);

    ResponseSalesBoard findSalesBoardById(Long id);

    void modifySalesBoard(Long id, RequestModifySalesBoard requestModifySalesBoard);


    void saveArticleBoard(RequestArticleBoard requestArticleBoard, User user);

    ResponseArticleBoard findArticleBoardById(Long id);

    void modifyArticleBoard(Long id, RequestModifyArticleBoard requestModifyArticleBoard);

    Page<Board> boardList(int page, String search, String type);
}
