package com.starshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starshop.entities.Role;
import com.starshop.utils.RoleName;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	 Optional<Role> findByName(String roleName);
}
