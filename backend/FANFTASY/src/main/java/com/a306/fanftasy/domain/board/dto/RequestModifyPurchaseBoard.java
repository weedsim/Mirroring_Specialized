package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestModifyPurchaseBoard {
    
    private String boardTitle;
    private String boardContent;
    private String filePath;
    private String fileType;

    public Board toEntity(Board board) {
        return Board.builder()
                .boardId(board.getBoardId())
                .boardWriteUserId(board.getBoardWriteUserId())
                .nftId(board.getNftId())
                .type(board.getType())
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardRegDate(board.getBoardRegDate())
                .views(board.getViews())
                .filePath(filePath)
                .fileType(fileType)
                .buyPrice(board.getBuyPrice())
                .salePrice(board.getSalePrice())
                .build();
    }
}
