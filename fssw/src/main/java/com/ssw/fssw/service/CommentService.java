package com.ssw.fssw.service;

import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommentApiRepository;
import com.ssw.fssw.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }


    public List<Comment> commentList(Long id) {
        return commentRepository.findComment(id);
    }


    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }


    public List<Comment> groupList(Long id) {
        return commentRepository.findGroupAll(id);
    }

    @Transactional
    public int groupCommentDelete(Long id) {
        return commentRepository.findGroupDeleteAll(id);
    }

}

