package com.starshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
