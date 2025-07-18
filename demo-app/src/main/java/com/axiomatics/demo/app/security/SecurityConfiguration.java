package com.axiomatics.demo.app.security;

import com.axiomatics.springboot.AxiomaticsSpringPep;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebMvc
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<UserDetails>();
        users.add(User.withDefaultPasswordEncoder().username("alice").password("password").roles("USER", "ADMIN").build());
        return new InMemoryUserDetailsManager(users);
    }

}
