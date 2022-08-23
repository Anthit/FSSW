package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityApiRepository extends JpaRepository<Community,Long> {
}
