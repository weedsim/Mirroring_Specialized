package com.a306.fanftasy.domain.comment.service;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.board.repository.BoardRepository;
import com.a306.fanftasy.domain.comment.dto.RequestComment;
import com.a306.fanftasy.domain.comment.dto.ResponseComment;
import com.a306.fanftasy.domain.comment.entity.Comment;
import com.a306.fanftasy.domain.comment.entity.CommentReport;
import com.a306.fanftasy.domain.comment.repository.CommentReportRepository;
import com.a306.fanftasy.domain.comment.repository.CommentRepository;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentReportRepository commentReportRepository;

    @Override
    public User findUserById(Long commentWriteUserId) {
        return userRepository.findById(commentWriteUserId).orElse(null);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    @Override
    public void saveComment(RequestComment requestComment, Board board, User user) {
        commentRepository.save(requestComment.toEntity(board, user));
    }

    @Override
    public void modifyComment(Long commentId, RequestComment requestComment) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw new RuntimeException();
        } else {
            commentRepository.save(requestComment.update(comment));
        }
    }

    @Override
    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<ResponseComment> commentList(Long boardId) {
        List<Comment> commentList = commentRepository.findByBoardId_BoardIdOrderByCommentRegDate(boardId);
        return commentList.stream().map(m -> ResponseComment.fromEntity(m))
                .collect(Collectors.toList());
//        List<ResponseComment> responseCommentList = commentList.stream().map(m -> ResponseComment.fromEntity(m))
//                .collect(Collectors.toList());
//        List<ResponseComment> result = new ArrayList<>();
//        for (Comment comment : commentList) {
//            boolean type = commentReportRepository.findType(user, comment);
//            result.add(ResponseComment.fromEntity(comment, type));
//        }
//        return result;
//        for (ResponseComment responseComment : responseCommentList) {
//            commentReportRepository.findType(user, responseComment);
//        }
    }

    @Override
    public Boolean findCommentReportType(User user, Comment comment) {
        int count = commentReportRepository.findCommentReportType(user, comment);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void saveCommentReport(User user, Comment comment) {
        commentReportRepository.save(new CommentReport(comment, user));
    }

    @Override
    public void removeCommentReport(User user, Comment comment) {
        commentReportRepository.deleteCommentReport(user, comment);
    }



}
