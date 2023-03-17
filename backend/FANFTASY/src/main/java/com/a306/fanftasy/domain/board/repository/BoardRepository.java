package com.a306.fanftasy.domain.board.repository;

import com.a306.fanftasy.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Board b SET b.views = :views where b.boardId = :id")
    void updateBoardViews(Long id, int views);

}
