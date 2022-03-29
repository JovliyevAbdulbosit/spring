package com.security.jwtlearning.repository;

import com.security.jwtlearning.domen.Card;
import com.security.jwtlearning.domen.TestUchun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TestUchunRepository extends JpaRepository<TestUchun,Long> {

}
