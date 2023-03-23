package com.a306.fanftasy.domain.board.entity;

import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder

public class BoardReport {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_report_id")
    private Long boardReportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_report_user_id", referencedColumnName = "user_id")
    private User boardReportUserId; // 신고자

    public BoardReport(Board boardId, User boardReportUserId) {
        this.boardId = boardId;
        this.boardReportUserId = boardReportUserId;
    }
}
