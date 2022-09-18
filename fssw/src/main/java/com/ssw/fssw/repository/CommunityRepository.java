package com.ssw.fssw.repository;


import com.ssw.fssw.domain.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityRepository {

    private final EntityManager em;

    public void save(Community community) {
        em.persist(community);
    }

    public Community findOne(Long id) {
        return em.find(Community.class, id);
    }

    public List<Community> findAll() {
        return em.createQuery("select c from Community c", Community.class)
                .getResultList();
    }
}
