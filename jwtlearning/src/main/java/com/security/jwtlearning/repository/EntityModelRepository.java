package com.security.jwtlearning.repository;

import com.security.jwtlearning.domen.EntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityModelRepository extends JpaRepository<EntityModel ,Long> {
}
