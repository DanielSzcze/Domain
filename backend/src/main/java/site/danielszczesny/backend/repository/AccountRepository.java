package site.danielszczesny.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.danielszczesny.backend.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountByUsername(String username);
}
