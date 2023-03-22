package com.a306.fanftasy.domain.comment.repository;

import com.a306.fanftasy.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardId_BoardIdOrderByCommentRegDate(Long boardId);
}
