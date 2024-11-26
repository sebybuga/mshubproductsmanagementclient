package com.hubproductsmanagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration  {

    private final UserConfig userConfig;

    public SecurityConfiguration(UserConfig userConfig) {
        this.userConfig = userConfig;
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // InMemoryUserDetailsManager setup simplified with only 2 users
        UserDetails admin = User.withUsername(userConfig.getUserStaff())
                .password(encoder.encode(userConfig.getPassAdmin()))
                .roles(userConfig.getRoleAdmin(), userConfig.getRoleStaff())
                .build();

        UserDetails user = User.withUsername(userConfig.getPassAdmin())
                .password(encoder.encode(userConfig.getPassAdmin()))
                .roles(userConfig.getRoleStaff())
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/hub-products-management/api/**").authenticated());
        return http.build();

    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

