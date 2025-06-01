package com.global.globalSoluction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.globalSoluction.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}