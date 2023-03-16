package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.RequestPurchaseBoard;
import com.a306.fanftasy.domain.board.dto.ResponsePurchaseBoard;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.board.repository.BoardRepository;
import com.a306.fanftasy.domain.user.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

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
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new RuntimeException();
        } else {
            ResponsePurchaseBoard responsePurchaseBoard = new ResponsePurchaseBoard();
            return responsePurchaseBoard.fromEntity(board.get());
        }
    }

    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Override
    public void removePurchaseBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
