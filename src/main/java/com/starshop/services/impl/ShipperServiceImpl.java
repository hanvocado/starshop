package com.starshop.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Role;
import com.starshop.entities.User;
import com.starshop.repositories.RoleRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.ShipperService;
import com.starshop.utils.RoleName;

@Service
public class ShipperServiceImpl implements ShipperService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<User> findAllShippers() {
		Optional<Role> shipperRole = roleRepository.findByName(RoleName.SHIPPER.name());
		Role role = new Role();
		if (shipperRole.isEmpty()) {
			role.setName(RoleName.SHIPPER.name());
			role = roleRepository.save(role);
		} else {
			role = shipperRole.get();
		}
		
		return userRepository.findByRole(role);
	}

}
