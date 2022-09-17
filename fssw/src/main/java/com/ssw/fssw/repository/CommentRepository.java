package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Comment> findComment(Long id) {
        //2차원 정렬로 내림차순으로 그룹은 하면서 , 그 다음 정렬은 코드로 나누는 것을 참고했다.
        String s = "select o from Comment o where comment_community_id=" + id + " order by comment_group desc , comment_code asc";
        return em.createQuery(s, Comment.class).getResultList();
    }

    public List<Comment> findGroupAll(Long id) {
        //해당 그룹의 값을 찾아주면 댓글 밑 대댓글 삭제까지 구현이 완료된다. 앞으로 수정해야하는 상황.
        String s = "SELECT o from Comment o group by comment_group HAVING comment_group =" + id;
        return em.createQuery(s, Comment.class).getResultList();
    }

    public int findGroupDeleteAll(Long id) {
        //해당 그룹의 값을 찾아주면 댓글 밑 대댓글 삭제까지 구현이 완료된다.
        String s = "delete Comment where comment_group="+id;
        return em.createQuery(s).executeUpdate();

    }

}
