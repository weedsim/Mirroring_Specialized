package com.a306.fanftasy.domain.comment.controller;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.comment.dto.RequestComment;
import com.a306.fanftasy.domain.comment.dto.ResponseComment;
import com.a306.fanftasy.domain.comment.entity.Comment;
import com.a306.fanftasy.domain.comment.service.CommentService;
import com.a306.fanftasy.domain.response.ResponseDefault;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> commentSave(@RequestBody RequestComment requestComment) {
        ResponseDefault responseDefault = null;
        User user = commentService.findUserById(requestComment.getCommentWriteUserId());
        Board board = commentService.findBoardById(requestComment.getBoardId());

        if (user == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("유효한 유저가 아닙니다.")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else if (board == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("삭제된 게시물입니다.")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else {
            commentService.saveComment(requestComment, board, user);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("댓글 작성 성공!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> commentModify(@PathVariable("commentId") Long commentId, @RequestBody RequestComment requestComment) {
        ResponseDefault responseDefault = null;
        try {
            commentService.modifyComment(commentId, requestComment);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("댓글 수정 성공!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        } catch (Exception e) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("댓글 수정 실패!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> commentRemove(@PathVariable("commentId") Long commentId) {
        ResponseDefault responseDefault = null;
        Comment comment = commentService.findCommentById(commentId);
        if (comment == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("이미 삭제된 댓글입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else {
            commentService.removeComment(commentId);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("댓글 삭제 성공!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> commentList(@PathVariable("boardId") Long boardId) {
        ResponseDefault responseDefault = null;
        Board board = commentService.findBoardById(boardId);

        if (board == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("삭제된 게시물입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else {
            List<ResponseComment> responseCommentList = commentService.commentList(boardId);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("댓글 목록 조회 성공!")
                    .data(responseCommentList)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        }
    }

    @GetMapping("/report")
    public ResponseEntity<?> findCommentReportType(@Param("userId") Long userId, @Param("commentId") Long commentId) {
        ResponseDefault responseDefault = null;
        User user = commentService.findUserById(userId);
        Comment comment = commentService.findCommentById(commentId);

        if (user == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("유효하지 않은 사용자입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else if (comment == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("삭제된 댓글입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else {
            Boolean resultType = commentService.findCommentReportType(user, comment);
            if (resultType) {
                responseDefault = ResponseDefault.builder()
                        .success(true)
                        .messege("신고 취소하기")
                        .data(true)
                        .build();
            } else {
                responseDefault = ResponseDefault.builder()
                        .success(true)
                        .messege("신고하기")
                        .data(false)
                        .build();
            }
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        }

    }

    @PostMapping("/report")
    public ResponseEntity<?> commentReportSave(@Param("userId") Long userId, @Param("commentId") Long commentId) {
        ResponseDefault responseDefault = null;
        User user = commentService.findUserById(userId);
        Comment comment = commentService.findCommentById(commentId);

        if (user == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("유효하지 않은 사용자입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else if (comment == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("삭제된 댓글입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else {
            commentService.saveCommentReport(user, comment);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("댓글 신고 완료!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        }
    }

    @DeleteMapping("/report")
    public ResponseEntity<?> commentReportRemove(@Param("userId") Long userId, @Param("commentId") Long commentId) {
        ResponseDefault responseDefault = null;
        User user = commentService.findUserById(userId);
        Comment comment = commentService.findCommentById(commentId);

        if (user == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("유효하지 않은 사용자입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else if (comment == null) {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("삭제된 댓글입니다!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
        } else {
            commentService.removeCommentReport(user, comment);
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("신고 취소 완료!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        }
    }
}
