package com.example.EventManager.Config;


import com.example.EventManager.Repository.UserRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class ApplicationConfig {


    private final UserRepsitory userRepsitory;

    public ApplicationConfig( UserRepsitory userRepsitory) {
        this.userRepsitory = userRepsitory;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepsitory.findByEmail(username)
                .orElseThrow(() -> new  UsernameNotFoundException("User not found"));//if the username not found

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){//is the data access object which is responsible to fetch user details and encode password ...
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());//give it the userDetails to fetch
        authProvider.setPasswordEncoder(passwordEncoder());//give it the password on encoder
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
