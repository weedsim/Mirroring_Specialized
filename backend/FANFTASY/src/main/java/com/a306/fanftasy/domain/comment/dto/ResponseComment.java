package com.a306.fanftasy.domain.comment.dto;

import com.a306.fanftasy.domain.comment.entity.Comment;
import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseComment {

    private Long commentId;
    private String commentContent;
    private LocalDateTime commentRegDate;
    private UserPublicDTO commentWriteUserId;

    public static ResponseComment fromEntity(Comment comment) {
        return ResponseComment.builder()
                .commentId(comment.getCommentId())
                .commentContent(comment.getCommentContent())
                .commentRegDate(comment.getCommentRegDate())
                .commentWriteUserId(UserPublicDTO.fromEntity(comment.getCommentWriteUserId()))
                .build();
    }
}
