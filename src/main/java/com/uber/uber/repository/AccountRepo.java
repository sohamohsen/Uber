package com.uber.uber.repository;

import com.uber.uber.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Integer> {


   /*@Query("SELECT * FROM account WHERE email = :email")
    Account getAccountByEmail(String email);*/

    Account findByEmailIs(String email);
}
