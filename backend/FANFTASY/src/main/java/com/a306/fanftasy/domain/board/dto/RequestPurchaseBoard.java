package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestPurchaseBoard {

    private Long boardWriteUserId;
    private String type;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardRegDate;
    private int views;
    private String filePath;
    private String fileType;
    private Double buyPrice;

    public Board toEntity(User user) {
        return Board.builder()
                .boardWriteUserId(user)
                .type(type)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardRegDate(boardRegDate)
                .views(views)
                .filePath(filePath)
                .fileType(fileType)
                .buyPrice(buyPrice)
                .build();
    }
}
