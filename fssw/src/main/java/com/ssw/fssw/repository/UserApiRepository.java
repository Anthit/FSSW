package com.ssw.fssw.repository;

import com.ssw.fssw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApiRepository extends JpaRepository<User,Long> {

    boolean existsByEmailId(String emailId);

}
