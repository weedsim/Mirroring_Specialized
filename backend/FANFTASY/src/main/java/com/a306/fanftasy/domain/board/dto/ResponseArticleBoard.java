package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseArticleBoard {

    private Long boardId;
    private UserPublicDTO boardWriteUserId;
    private String type;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardRegDate;
    private int views;
    private String filePath;
    private String fileType;

    public static ResponseArticleBoard fromEntity(Board board) {
        return ResponseArticleBoard.builder()
                .boardId(board.getBoardId())
                .boardWriteUserId(UserPublicDTO.fromEntity(board.getBoardWriteUserId()))
                .type(board.getType())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .boardRegDate(board.getBoardRegDate())
                .views(board.getViews())
                .filePath(board.getFilePath())
                .fileType(board.getFileType())
                .build();
    }

}
