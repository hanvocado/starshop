package com.starshop.configs;

import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.DispatcherType;

import com.starshop.entities.Role;
import com.starshop.services.impl.JpaUserDetailsService;
import com.starshop.utils.Constants;
import com.starshop.utils.RoleName;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
	private final String[] PUBLIC_ENDPOINTS = { "/auth/login/**", "/auth/register/**", 
			"/admin/categories/**", "/admin/products/**", "/admin/vouchers/**" };

//	
//	@Autowired
//	@Lazy
//	private JwtFilter jwtFilter;
//
//	// Defines a UserDetailsService bean for user authentication
//	@Bean
//	@Lazy
//	public UserDetailsService userDetailsService() {
//		return new UserServiceImpl();
//	}
//
//	// Configures the security filter chain
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		log.warn("Đã vào securityconfig");
////		httpSecurity.formLogin(form -> form.loginPage("/auth/login").permitAll());
////		httpSecurity.csrf(csrf -> csrf.disable()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
////				.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().anyRequest().authenticated())
////				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////				.authenticationProvider(authenticationProvider());
////		return httpSecurity.build();
//
//		httpSecurity.csrf(csrf -> csrf.disable()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//				.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll()
//						.anyRequest().authenticated())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authenticationProvider(authenticationProvider());
//		log.warn("Đã qua securityconfig");
//		return httpSecurity.build();
//	}
//
//	// Creates a DaoAuthenticationProvider to handle user authentication
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService());
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
//
//	// Defines a PasswordEncoder bean that uses bcrypt hashing by default for
//	// password encoding
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
//
//	// Defines an AuthenticationManager bean to manage authentication processes
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//
	  @Autowired
	    private JpaUserDetailsService userDetailsService;


	    @Bean
	    public AuthenticationManager authManager() {

	        var authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return new ProviderManager(authProvider);
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
	        return http
	                .csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(authorize -> authorize
	                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
	                        .requestMatchers("/auth/login/**", "/auth/register/**").permitAll()
	                        .requestMatchers("/exec/**", "/img/**", "/shop/**").permitAll()
	                        .requestMatchers("/admin/**").hasRole(RoleName.ADMIN.name())
	                        .requestMatchers("/user/**").hasAuthority("USER")
	                        .anyRequest().authenticated()
	                )
	                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> 
	                        jwtConfigurer.decoder(jwtDecoder())
	                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
	                ))
	                .userDetailsService(userDetailsService)
	                .httpBasic(Customizer.withDefaults())
	                .build();
	    }

	    
	    @Bean
	    JwtAuthenticationConverter jwtAuthenticationConverter() {
	        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

	        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
	        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

	        return jwtAuthenticationConverter;
	    }
	    

	    @Bean
		JwtDecoder jwtDecoder() {
			  SecretKeySpec secretKeySpec = new SecretKeySpec(Constants.SIGNER_KEY.getBytes(), "HS512");
	          return NimbusJwtDecoder
	        		  .withSecretKey(secretKeySpec)
	                  .macAlgorithm(MacAlgorithm.HS512)
	                  .build();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(10);
	    }
	    
	    @Bean
	    HttpFirewall allowDoubleSlashHttpFirewall() {
	        StrictHttpFirewall firewall = new StrictHttpFirewall();
	        firewall.setAllowUrlEncodedDoubleSlash(true);
	        return firewall;
	    }

}
