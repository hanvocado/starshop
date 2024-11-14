package com.starshop.configs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.starshop.entities.Order;
import com.starshop.entities.User;
import com.starshop.repositories.UserRepository;
import com.starshop.services.OrderService;
import com.starshop.services.UserService;
import com.starshop.utils.OrderStatus;
import com.starshop.utils.PaymentType;
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserRepository userRepository;

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
			
			if (userRepository.findByUserName("shipper").isEmpty()) {
				User user = User.builder().userName("shipper").email("shipper1@gmail.com")
						.password(passwordEncoder.encode("s12345")).build();
				userService.assignRole(user, RoleName.SHIPPER.name());

				userRepository.save(user);
			}
			
			if (userRepository.findByUserName("shipper2").isEmpty()) {
				User user = User.builder().userName("shipper2").email("shipper2@gmail.com")
						.password(passwordEncoder.encode("s12345")).build();
				userService.assignRole(user, RoleName.SHIPPER.name());

				userRepository.save(user);
			}
			
			long orderCount = orderService.count();

            if (orderCount < 10) {
                User user = createTestBuyerIfNotExists();

                createOrdersForUser(user, 10);
            }
		};
	}
	
	@Transactional
    private User createTestBuyerIfNotExists() {
		if (userRepository.findByUserName("testbuyer").isEmpty()) {
			User user = User.builder().userName("testbuyer").email("testbuyer@gmail.com")
					.password(passwordEncoder.encode("12345")).build();
			userService.assignRole(user, RoleName.USER.name());

			return userRepository.save(user);
		} else
			return userRepository.findByEmail("testbuyer@gmail.com").get();
    }
	
	@Transactional
    private void createOrdersForUser(User user, int numberOfOrders) {
		List<OrderStatus> statuses = List.of(OrderStatus.PENDING, OrderStatus.DELIVERED, OrderStatus.SHIPPING, OrderStatus.SHIPFAILED);
        IntStream.rangeClosed(1, numberOfOrders).forEach(i -> {
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now().minusDays(i));
            
         // Assign different statuses in a rotating manner
            OrderStatus status = statuses.get(i % statuses.size());
            order.setStatus(status);
            
            if (order.getStatus() != OrderStatus.PENDING)
            	order.setShipper(userRepository.findByEmail("shipper1@gmail.com").get());
            
            order.setTotalAmount(100 + i);  // Example amount
            order.setPayMethod(PaymentType.CASH);  // Example payment method
            order.setPayed(false);  // Alternate payment status
            orderService.add(order);
        
        });
    }

}
