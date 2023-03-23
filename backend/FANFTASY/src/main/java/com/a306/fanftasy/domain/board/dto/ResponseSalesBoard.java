package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSalesBoard {

    private Long boardId;

    private UserPublicDTO boardWriteUserId;

    private NFT nftId;

    private String type;

    private String boardTitle;

    private String boardContent;

    private LocalDateTime boardRegDate;

    private int views;

    private String filePath;

    private String fileType;

    private Double salePrice;

    public ResponseSalesBoard fromEntity(Board board) {
        return ResponseSalesBoard.builder()
                .boardId(board.getBoardId())
                .boardWriteUserId(UserPublicDTO.fromEntity(board.getBoardWriteUserId()))
                .nftId(board.getNftId())
                .type(board.getType())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .boardRegDate(board.getBoardRegDate())
                .views(board.getViews())
                .filePath(board.getFilePath())
                .fileType(board.getFileType())
                .salePrice(board.getSalePrice())
                .build();
    }
}
