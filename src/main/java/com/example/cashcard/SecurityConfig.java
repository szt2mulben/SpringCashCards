package com.example.cashcard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/cashcards/**")
                .hasRole("CARD-OWNER")
                .and()
                .csrf().disable()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails kumar = users
                .username("teszt1")
                .password(passwordEncoder.encode("tesztjelszo1"))
                .roles("CARD-OWNER")
                .build();
        UserDetails sankarNonOwner = users
                .username("teszt2")
                .password(passwordEncoder.encode("tesztjelszo2"))
                .roles("NON-OWNER")
                .build();
        UserDetails kumar2 = users
                .username("teszt3")
                .password(passwordEncoder.encode("tesztjelszo3"))
                .roles("CARD-OWNER")
                .build();

        return new InMemoryUserDetailsManager(teszt1, teszt2, teszt12);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
