package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.*;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.board.repository.BoardRepository;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Board findBoard = boardRepository.findById(id).orElse(null);
        if (findBoard == null) {
            throw new RuntimeException();
        } else {
            updateBoardViews(id, findBoard.getViews());
            Board board = boardRepository.findById(id).orElse(null);
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
    public void removeBoard(Long id) {
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
        Board findboard = boardRepository.findById(id).orElse(null);
        if (findboard == null) {
            throw new RuntimeException();
        } else {
            updateBoardViews(id, findboard.getViews());
            Board board = boardRepository.findById(id).orElse(null);
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

    @Override
    public void saveArticleBoard(RequestArticleBoard requestArticleBoard, User user) {
        boardRepository.save(requestArticleBoard.toEntity(user));
    }

    @Override
    public ResponseArticleBoard findArticleBoardById(Long id) {
        Board findboard = boardRepository.findById(id).orElse(null);
        if (findboard == null) {
            throw new RuntimeException();
        } else {
            updateBoardViews(id, findboard.getViews());
            Board board = boardRepository.findById(id).orElse(null);
            ResponseArticleBoard responseArticleBoard = new ResponseArticleBoard();
            return responseArticleBoard.fromEntity(board);
        }
    }

    @Override
    public void modifyArticleBoard(Long id, RequestModifyArticleBoard requestModifyArticleBoard) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new RuntimeException();
        } else {
            Board boardEntity = requestModifyArticleBoard.toEntity(board);
            boardRepository.save(boardEntity);
        }
    }

    @Override
    public Page<Board> boardList(int page, String search, String type) {
        Pageable pageRequest = PageRequest.of(page, 10, Sort.Direction.DESC, "boardRegDate");
        Page<Board> boardList = boardRepository.findByBoardTitleContainingAndTypeContaining(search, pageRequest, type);
        return boardList;
    }
}
