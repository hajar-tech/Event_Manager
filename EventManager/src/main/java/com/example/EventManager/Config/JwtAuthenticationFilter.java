package com.example.EventManager.Config;

import com.example.EventManager.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor//create constructors using the private final field

//in order to make the filter work every time when the user send a request we use OncePerRequestFilter
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //check if we have a token
        final  String authHeader = request.getHeader("Authorization");//Authorization is the header contain the jwt token
        final  String token;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {//should start with Bearer .
            filterChain.doFilter(request, response);
            return;
        }

        //extrat the token from the authHeader
        token = authHeader.substring(7);//start the count from Bearer and the space

        //to extract the userEmail from twt token i need a class to implement the token "JwtService"
        userEmail = jwtService.extractUsername(token) ;
    }
}
