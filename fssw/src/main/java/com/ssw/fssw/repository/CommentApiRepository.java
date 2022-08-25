package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentApiRepository extends JpaRepository<Comment,Long> {
}
