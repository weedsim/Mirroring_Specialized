package com.a306.fanftasy.domain.comment.entity;

import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class CommentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_report_id")
    private Long commentReportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment commentId; // 댓글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_report_user_id", referencedColumnName = "user_id")
    private User commentReportUserId; // 신고자 ID

    public CommentReport(Comment commentId, User commentReportUserId) {
        this.commentId = commentId;
        this.commentReportUserId = commentReportUserId;
    }
}
