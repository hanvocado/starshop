package com.starshop.configs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.starshop.entities.User;
import com.starshop.services.JwtService;
import com.starshop.services.UserService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

	private final String[] PUBLIC_ENDPOINTS = { "/auth/login/**", "/auth/register/**", 
			"/products/**", "/exec/**", "/img/**", "/shop/**" };
	
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    	String requestURI = request.getRequestURI();
        for (String publicEndpoint : PUBLIC_ENDPOINTS) {
            if (requestURI.startsWith(publicEndpoint.replace("/**", ""))) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        
        try {
        	
        	if (request.getServletPath().equals("/auth/login")) {
                filterChain.doFilter(request, response);
                return;
            }
        	
            String jwt = jwtService.getJwtFromCookies(request);

            if (jwt != null) {
                String username = jwtService.getUserNameFromJwt(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);

                    if (jwtService.validateToken(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // Thiết lập đối tượng Authentication vào SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        response.sendRedirect("/auth/login");
                        return;
                    }
                }
            } else {
                // Nếu không tìm thấy JWT, chuyển hướng đến trang login
                response.sendRedirect("/auth/login");
                return;
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
            response.sendRedirect("/auth/login");
            return;
        }

        // Tiếp tục chuỗi bộ lọc
        filterChain.doFilter(request, response);
    }

}
