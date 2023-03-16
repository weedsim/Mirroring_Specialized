package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.nft.NFT;
import com.a306.fanftasy.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 구매글 세부조회 resp data
 */
public class ResponsePurchaseBoard {

    private Long boardId;
    private User boardWriteUserId;
    private String type;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardRegDate;
    private int views;
    private String filePath;
    private String fileType;

    public ResponsePurchaseBoard fromEntity(Board board) {
        return ResponsePurchaseBoard.builder()
                .boardId(board.getBoardId())
                .boardWriteUserId(board.getBoardWriteUserId())
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
