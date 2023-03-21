package com.a306.fanftasy.domain.comment.repository;

import com.a306.fanftasy.domain.comment.entity.Comment;
import com.a306.fanftasy.domain.comment.entity.CommentReport;
import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from CommentReport c where c.commentReportUserId =:user and c.commentId =:comment")
    void deleteCommentReport(@Param("user") User user, @Param("comment") Comment comment);

    @Query("select count(*) from CommentReport c where c.commentReportUserId =:user and c.commentId =:comment")
    int findCommentReportType(User user, Comment comment);


//    @Query("select c.commentReportId from CommentReport c where c.commentReportUserId =:user and c.commentId =:comment")
//    Boolean findCommentReportCheckByUserAndByComment(@Param("user") User user, @Param("comment") Comment comment);
//    default boolean findType(User user, Comment comment) {
//        Boolean id = findCommentReportCheckByUserAndByComment(user, comment);
//        return id != null && id;
//    }

}
