//package com.starshop.configs;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.data.util.Pair;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import com.starshop.services.JwtService;
//import com.starshop.services.UserService;
//
//import io.micrometer.common.lang.NonNull;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
////@Component
////@Slf4j
////public class JwtFilter extends OncePerRequestFilter {
////	private final HandlerExceptionResolver handlerExceptionResolver;
////	private final JwtService jwtService;
////	private final UserDetailsService userDetailsService;
////
////	public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService,
////			HandlerExceptionResolver handlerExceptionResolver) {
////		this.jwtService = jwtService;
////		this.userDetailsService = userDetailsService;
////		this.handlerExceptionResolver = handlerExceptionResolver;
////	}
////
////	@Override
////	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
////			@NonNull FilterChain filterChain) throws ServletException, IOException {
////		final String authHeader = request.getHeader("Authorization");
////
////		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////			log.warn("qua luon");
////			filterChain.doFilter(request, response);
////			return;
////		}
////		try {
////			final String jwt = authHeader.substring(7);
////			final String userEmail = jwtService.extractEmail(jwt);
////			log.warn("đã tách email: {}", userEmail);
////
////			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////			if (userEmail != null && authentication == null) {
////				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
////
////				if (jwtService.validateToken(jwt, userDetails)) {
////					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
////							null, userDetails.getAuthorities());
////
////					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////					SecurityContextHolder.getContext().setAuthentication(authToken);
////				}
////			}
////
////			filterChain.doFilter(request, response);
////		} catch (Exception exception) {
////			handlerExceptionResolver.resolveException(request, response, null, exception);
////		}
//
////		try {
//////			if (isBypassToken(request)) {
//////				filterChain.doFilter(request, response);
//////				return;
//////			}
////
////			final String authHeader = request.getHeader("Authorization");
////			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
////				return;
////			}
////			final String token = authHeader.substring(7);
////			final String userEmail = jwtService.extractEmail(token);
////			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
////				if (jwtService.validateToken(token, userDetails)) {
////					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
////							null, userDetails.getAuthorities());
////
////					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////					SecurityContextHolder.getContext().setAuthentication(authToken);
////				}
////			}
////			filterChain.doFilter(request, response);
////		} catch (Exception exception) {
////			handlerExceptionResolver.resolveException(request, response, null, exception);
////		}
////	}
//
////	public boolean isBypassToken(HttpServletRequest request) {
////		final List<Pair<String, String>> bypassTokens = Arrays.asList(Pair.of("auth/login", "GET"));
////
////		String requestPath = request.getServletPath();
////		String requestMethod = request.getMethod();
////
////		return bypassTokens.stream()
////				.anyMatch(pair -> pair.getFirst().equals(requestPath) && pair.getSecond().equals(requestMethod));
////	}
////	}
////}
//
//@Component
//@Slf4j
//public class JwtFilter extends OncePerRequestFilter {
//	@Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private ApplicationContext applicationContext;
//    
//    @Autowired
//    private final HandlerExceptionResolver handlerExceptionResolver;
//    
//    @Autowired
//    private final UserDetailsService userDetailsService;
//    
//    private UserService getUserService() {
//        return applicationContext.getBean(UserService.class);
//    }
//
//    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService,
//                     HandlerExceptionResolver handlerExceptionResolver) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//        this.handlerExceptionResolver = handlerExceptionResolver;
//    }
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            log.warn("Authorization header is missing or does not start with Bearer");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            final String jwt = authHeader.substring(7);
//            final String userEmail = jwtService.extractEmail(jwt);
//            log.warn("Extracted email: {}", userEmail);
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (userEmail != null && authentication == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//
//                if (jwtService.validateToken(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//
//            filterChain.doFilter(request, response);
//        } catch (Exception exception) {
//            log.error("Error processing JWT token", exception);
//            handlerExceptionResolver.resolveException(request, response, null, exception);
//        }
//    	
////    	String authHeader = request.getHeader("Authorization");
////        String token = null;
////        String userName = null;
////
////        if (authHeader != null && authHeader.startsWith("Bearer ")) {
////            // Extracting the token from the Authorization header
////            token = authHeader.substring(7);
////            // Extracting username from the token
////            userName = jwtService.extractEmail(token);
////        }
////
////        // If username is extracted and there is no authentication in the current SecurityContext
////        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            // Loading UserDetails by username extracted from the token
////            UserDetails userDetails = getUserService().loadUserByUsername(userName);
////
////            // Validating the token with loaded UserDetails
////            if (jwtService.validateToken(token, userDetails)) {
////                // Creating an authentication token using UserDetails
////                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                // Setting authentication details
////                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                // Setting the authentication token in the SecurityContext
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }
////        }
////
////        // Proceeding with the filter chain
////        filterChain.doFilter(request, response);
//    }
//}
