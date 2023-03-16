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
/**
 * 구매글 등록시 req data
 */
public class RequestPurchaseBoard {

    private Long boardWriteUserId;
    private String type;
    private String boardTitle;
    private String boardContent;
//    private LocalDateTime boardRegDate = LocalDateTime.now();
//    private int views;
    private String filePath;
    private String fileType;

    public Board toEntity(User user) {
        return Board.builder()
                .boardWriteUserId(user)
                .type(type)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardRegDate(LocalDateTime.now())
                .views(0)
                .filePath(filePath)
                .fileType(fileType)
                .build();
    }
}
