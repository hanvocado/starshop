package com.starshop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starshop.entities.User;
import com.starshop.repositories.UserRepository;
import com.starshop.services.UserService;
import com.starshop.utils.RoleName;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
	// Khởi tạo tài khoản Admin khi khởi động ứng dụng nếu chưa có tài khoản Admin

	private PasswordEncoder passwordEncoder;
	private UserService userService;

	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			if (userRepository.findByUserName("admin").isEmpty()) {
				User user = User.builder().userName("admin").email("admin@gmail.com")
						.password(passwordEncoder.encode("admin123")).build();
				userService.assignRole(user, RoleName.ADMIN.name());

				userRepository.save(user);
				log.warn("Admin has been created with default password: admin123, please change it");
			}
		};
	}

}
