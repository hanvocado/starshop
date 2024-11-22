package com.starshop.configs;

import java.util.Arrays;
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
			"/products/**", "/exec/**", "/img/**", "/shop/**" };
	
	  @Autowired
	  @Lazy
	  private JwtFilter jwtFilter;
	
	  @Autowired
	    private JpaUserDetailsService userDetailsService;


	  @Bean
	  public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
	      return config.getAuthenticationManager();
	  }
	  
	  @Bean
	  public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder);
	      return authProvider;
	  }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
	        return http
	                .csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(authorize -> authorize
	                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
	                        .requestMatchers("/**").permitAll()
	                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
	                        .requestMatchers("/admin/**").hasRole(RoleName.ADMIN.name())
	                        .requestMatchers("/user/**").hasAuthority("USER")
	                        .anyRequest().authenticated()
	                )
	                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
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
