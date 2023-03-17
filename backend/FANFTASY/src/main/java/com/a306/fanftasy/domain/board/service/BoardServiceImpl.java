package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.*;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.board.repository.BoardRepository;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final NFTRepository nftRepository;


    /**
     * User -> UserDto 변경 수정 필요
     */
    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void savePurchaseBoard(RequestPurchaseBoard requestPurchaseBoard, User user) {
        boardRepository.save(requestPurchaseBoard.toEntity(user));
    }

    @Override
    public ResponsePurchaseBoard findPurchaseBoardById(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new RuntimeException();
        } else {
            updateBoardViews(id, board.getViews());
            ResponsePurchaseBoard responsePurchaseBoard = new ResponsePurchaseBoard();
            return responsePurchaseBoard.fromEntity(board);
        }
    }

    @Override
    public void updateBoardViews(Long id, int views) {
        boardRepository.updateBoardViews(id, views+1);
    }


    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Override
    public void removePurchaseBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public void modifyPurchaseBoard(Long id, RequestModifyPurchaseBoard requestModifyPurchaseBoard) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new RuntimeException();
        } else {
            Board boardEntity = requestModifyPurchaseBoard.toEntity(board);
            boardRepository.save(boardEntity);
        }
    }

    @Override
    public NFT findNFTById(Long nftId) {
        return nftRepository.findById(nftId).orElse(null);
    }

    @Override
    public void saveSalesBoard(RequestSalesBoard requestSalesBoard, User user, NFT nft) {
        boardRepository.save(requestSalesBoard.toEntity(user, nft));
    }

    @Override
    public ResponseSalesBoard findSalesBoardById(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new RuntimeException();
        } else {
            updateBoardViews(id, board.getViews());
            ResponseSalesBoard responseSalesBoard = new ResponseSalesBoard();
            return responseSalesBoard.fromEntity(board);
        }
    }

    @Override
    public void modifySalesBoard(Long id, RequestModifySalesBoard requestModifySalesBoard) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new RuntimeException();
        } else {
            Board boardEntity = requestModifySalesBoard.toEntity(board);
            boardRepository.save(boardEntity);
        }
    }
}
