package com.uber.uber.repository;

import com.uber.uber.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;


//calling database
public interface AccountRepo extends JpaRepository<Account,Integer> {

    //select * from account where email = :email
    Account findByEmailIs(String email);   //instate of query

}
