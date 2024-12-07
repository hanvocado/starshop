package com.starshop.configs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.starshop.entities.*;
import com.starshop.repositories.CartRepository;
import com.starshop.repositories.ProductRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.OrderService;
import com.starshop.services.UserService;
import com.starshop.utils.PaymentType;

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
	private OrderService orderService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			createSeedUsers();
						
			long orderCount = orderService.count();
			long productCount = productRepository.count();

            if (orderCount < 10 && productCount >= 10) {
                Customer user = createTestBuyerIfNotExists();

                createOrdersForUser(user, 10);
            }
		};
	}
	
	private void createSeedUsers() {
		if (userRepository.findByUserName("admin").isEmpty()) {
			Admin user = Admin.builder().userName("admin").email("admin@gmail.com")
					.password(passwordEncoder.encode("admin123")).build();

			userRepository.save(user);
			log.warn("Admin has been created with default password: admin123, please change it");
		}
		
		if (userRepository.findByUserName("shipper").isEmpty()) {
			Shipper user = Shipper.builder().userName("shipper").email("shipper1@gmail.com")
					.password(passwordEncoder.encode("s12345")).build();

			userRepository.save(user);
		}
		
		if (userRepository.findByUserName("shipper2").isEmpty()) {
			Shipper user = Shipper.builder().userName("shipper2").email("shipper2@gmail.com")
					.password(passwordEncoder.encode("s12345")).build();

			userRepository.save(user);
		}
	}
	
	@Transactional
	private Customer createTestBuyerIfNotExists() {
	    if (userRepository.findByUserName("testbuyer").isEmpty()) {
	        Customer user = Customer.builder()
	                .userName("testbuyer")
	                .email("testbuyer@gmail.com")
	                .password(passwordEncoder.encode("12345"))
	                .build();
	        user = userRepository.save(user);

	        Cart newCart = new Cart();
	        newCart.setUser(user);
	        cartRepository.save(newCart);

	        return user;
	    } else {
	        return (Customer) userRepository.findByEmail("testbuyer@gmail.com").get();
	    }
	}
	
	@Transactional
    private void createOrdersForUser(Customer user, int numberOfOrders) {
        IntStream.rangeClosed(1, numberOfOrders).forEach(i -> {
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now().minusDays(i));
          
            order.setPayMethod(PaymentType.CASH);  
            order.setPayed(false);  
            Product product = productRepository.findById((long) i).get();
            order.addProductLine(new ProductLine(product, order, 2));
            order.setShippingFee(i*2000);
            orderService.add(order);        
        });
    }

}
