package com.ssw.fssw.repository.board;

import com.ssw.fssw.domain.Board;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaBoardRepository implements BoardRepository{
    private final EntityManager em;

    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public List<Object> findAllBoardList(int offset, int limit) {
        return em.createQuery("select b.type, b.title from BOARD b order by b.num DESC")
               .setFirstResult(offset)
               .setMaxResults(limit)
               .getResultList();
    }

    @Override
    public Optional<Board> findByNum(long num) {
        Board board = em.find(Board.class, num);
        return Optional.ofNullable(board);
    }
}
