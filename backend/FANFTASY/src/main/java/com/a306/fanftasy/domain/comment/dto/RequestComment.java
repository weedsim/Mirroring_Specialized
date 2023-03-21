package com.a306.fanftasy.domain.comment.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.comment.entity.Comment;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestComment {

    private String commentContent;
//    private LocalDateTime commentRegDate;
    private Long boardId;
    private Long commentWriteUserId;

    public Comment toEntity(Board board, User user) {
        return Comment.builder()
                .commentContent(commentContent)
                .commentRegDate(LocalDateTime.now())
                .boardId(board)
                .commentWriteUserId(user)
                .build();
    }

    public Comment update(Comment comment) {
        return Comment.builder()
                .commentId(comment.getCommentId())
                .commentContent(commentContent)
                .commentRegDate(comment.getCommentRegDate())
                .boardId(comment.getBoardId())
                .commentWriteUserId(comment.getCommentWriteUserId())
                .build();
    }
}
