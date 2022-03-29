package com.security.jwtlearning.services;

import com.security.jwtlearning.domen.AccountCardUser;
import com.security.jwtlearning.domen.UserTable;
import com.security.jwtlearning.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AccountService  {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
public AccountCardUser accountCreate(AccountCardUser accountCardUser){
     return    accountRepository.save(accountCardUser);
}
public List<AccountCardUser> getAll(){
        return accountRepository.findAll();
}
public AccountCardUser findByUserid(UserTable userid  ){
        return accountRepository.findByUserid(userid);
}
}
