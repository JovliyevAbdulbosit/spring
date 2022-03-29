package com.security.jwtlearning.repository;

import com.security.jwtlearning.domen.AccountCardUser;
import com.security.jwtlearning.domen.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;
@Repository
public interface AccountRepository extends JpaRepository<AccountCardUser , Long> {
     AccountCardUser findByUserid(UserTable userid );

}
