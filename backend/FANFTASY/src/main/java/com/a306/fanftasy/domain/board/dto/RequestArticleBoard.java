package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestArticleBoard {

    private Long boardWriteUserId;
    private String type;
    private String boardTitle;
    private String boardContent;
//    private LocalDateTime boardRegDate;
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
                .buyPrice(0.0)
                .salePrice(0.0)
                .build();
    }
}
