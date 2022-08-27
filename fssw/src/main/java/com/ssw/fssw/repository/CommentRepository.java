package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll(Long id) {
        return em.createQuery("select o from Comment o", Comment.class)
                .getResultList();
    }

    public List<Comment> findComment(Long id){
        String s="select o from Comment o where comment_community_id="+id;
        return em.createQuery(s,Comment.class).getResultList();
    }
}
