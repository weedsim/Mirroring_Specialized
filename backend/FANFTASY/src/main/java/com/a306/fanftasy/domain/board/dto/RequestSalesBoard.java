package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestSalesBoard {

    private Long boardWriteUserId;

    private Long nftId;

    private String type;

    private String boardTitle;

    private String boardContent;

//    private LocalDateTime boardRegDate;

//    private int views;

    private String filePath;

    private String fileType;

    private Double salePrice;

    public Board toEntity(User user, NFT nft) {
        return Board.builder()
                .boardWriteUserId(user)
                .nftId(nft)
                .type(type)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardRegDate(LocalDateTime.now())
                .views(0)
                .filePath(filePath)
                .fileType(fileType)
                .buyPrice(0.0)
                .salePrice(salePrice)
                .build();
    }
}
