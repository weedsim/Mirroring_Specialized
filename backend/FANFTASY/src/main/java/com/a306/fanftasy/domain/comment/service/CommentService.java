package com.a306.fanftasy.domain.comment.service;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.comment.dto.RequestComment;
import com.a306.fanftasy.domain.comment.dto.ResponseComment;
import com.a306.fanftasy.domain.comment.entity.Comment;
import com.a306.fanftasy.domain.user.entity.User;

import java.util.List;

public interface CommentService {
    User findUserById(Long commentWriteUserId);

    Comment findCommentById(Long commentId);

    Board findBoardById(Long boardId);

    void saveComment(RequestComment requestComment, Board board, User user);

    void modifyComment(Long commentId, RequestComment requestComment);

    void removeComment(Long commentId);

    List<ResponseComment> commentList(Long boardId);

    void saveCommentReport(User user, Comment comment);

    void removeCommentReport(User userId, Comment commentId);

    Boolean findCommentReportType(User user, Comment comment);
}
