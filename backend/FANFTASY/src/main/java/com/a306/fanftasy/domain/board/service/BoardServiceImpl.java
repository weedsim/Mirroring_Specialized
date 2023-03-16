package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.RequestPurchaseBoard;
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
    public void savePurchaseBoard(RequestPurchaseBoard purchaseBoard) {
        Optional<User> findUser = userRepository.findById(purchaseBoard.getBoardWriteUserId());
        boardRepository.save(purchaseBoard.toEntity(findUser.get()));
    }
}
