//package com.ssw.fssw.repository.account;
//
//import com.ssw.fssw.domain.Account;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface SpringDataJpaAccountRepository extends JpaRepository<Account, Long>, AccountRepository {
//
//    // JPQL select a from Account as a where a.email = ?
//    @Override
//    Optional<Account> findByEmail(String email);
//}
