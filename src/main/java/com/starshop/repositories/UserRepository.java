package com.starshop.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
