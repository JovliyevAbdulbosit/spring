package com.security.jwtlearning.repository;

import com.security.jwtlearning.domen.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable,Long> {
    UserTable findByUsername(String name);
    boolean existsByUsername( String userName);
}
