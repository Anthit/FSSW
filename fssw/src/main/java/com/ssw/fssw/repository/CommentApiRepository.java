package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentApiRepository extends JpaRepository<Comment,Long> {

    List<Comment> findLastById(Long id);
}
