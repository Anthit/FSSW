package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentApiRepository extends JpaRepository<Comment,Long> {

    @Query("select coalesce(max(COMMENT_CODE),0)+1 from Comment")
    Long getCommentLastId();

    @Query("select max(comment_order)+1 from Comment")
    int getCommentLastOrder();
}
