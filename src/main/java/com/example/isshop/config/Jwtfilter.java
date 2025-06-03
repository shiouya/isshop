package com.example.isshop.config;

import com.example.isshop.entity.Users;
import com.example.isshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class Jwtfilter  extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private Jwtutil jwtService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 取得 header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(BEARER_PREFIX.length());
        try {
            Claims claims=jwtService.parseToken(jwt);
            String username = claims.getSubject();
            // 如果 SecurityContext 尚未有認證，建立認證物件
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 這裡應該根據你的邏輯載入使用者資訊
                Users user = userRepository.findByUserName(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );
                // 建立安全上下文
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        } catch (JwtException e) {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
