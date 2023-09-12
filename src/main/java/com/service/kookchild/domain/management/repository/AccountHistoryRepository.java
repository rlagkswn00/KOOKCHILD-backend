package com.service.kookchild.domain.management.repository;

import com.service.kookchild.domain.management.domain.Account;
import com.service.kookchild.domain.management.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

    List<AccountHistory> findAccountHistoriesByAccount(Account account);
   @Query("SELECT SUM(a.amount) FROM AccountHistory a WHERE a.category IN(:name) AND a.userId = :id")
    Long findAmount(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT NVL(SUM(a.amount), 0) FROM AccountHistory a WHERE a.category NOT IN(:name) AND a.userId = :id")
    Long findNotInAmount(@Param("id") Long id, @Param("name") String name);
}
