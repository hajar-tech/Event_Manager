package com.example.EventManager.filter;



import com.example.EventManager.configuration.JwtUtils;
import com.example.EventManager.service.CustomUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component

public class JwtFilter extends OncePerRequestFilter {

    private final CustomUserService custemUserService;
    private final JwtUtils jwtUtils;

@Autowired
    public JwtFilter(CustomUserService custemUserService, JwtUtils jwtUtils) {
        this.custemUserService = custemUserService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;





        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = custemUserService.loadUserByUsername(username);
            if (jwtUtils.validateToken(jwt, userDetails)) {
                String role = jwtUtils.extractRole(jwt);
                if (role != null) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }else {
                    System.out.println(" Le rôle est null dans le token.");
                }
                }
        }



        filterChain.doFilter(request, response);
    }
}
