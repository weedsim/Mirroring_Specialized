package com.a306.fanftasy.domain.board.dto;

import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBoard {

    private Long boardId;
    private UserPublicDTO boardWriteUserId;
    private String type;
    private String boardTitle;
    private LocalDateTime boardRegDate;
    private int views;
    private String fileType; // 유저가 올린 파일 타입, NFT (X)

    public Page<ResponseBoard> toDtoList(Page<Board> boardList) {
        Page<ResponseBoard> boardDtoList = boardList.map(m -> ResponseBoard.builder()
                .boardId(m.getBoardId())
                .boardWriteUserId(UserPublicDTO.fromEntity(m.getBoardWriteUserId()))
                .type(m.getType())
                .boardTitle(m.getBoardTitle())
                .boardRegDate(m.getBoardRegDate())
                .views(m.getViews())
                .fileType(m.getFileType())
                .build());
        return boardDtoList;
    }
}
