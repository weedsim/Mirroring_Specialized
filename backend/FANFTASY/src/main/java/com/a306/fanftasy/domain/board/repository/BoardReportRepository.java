package com.a306.fanftasy.domain.board.repository;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.board.entity.BoardReport;
import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from BoardReport b where b.boardReportUserId =:user and b.boardId =:board")
    void deleteBoardReport(User user, Board board);

    @Query("select count(*) from BoardReport b where b.boardReportUserId =:user and b.boardId =:board")
    int findBoardReportType(User user, Board board);
}
