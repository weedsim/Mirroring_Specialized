package com.a306.fanftasy.domain.board.repository;

import com.a306.fanftasy.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
