package com.hubproductsmanagement.config;


import com.hubproductsmanagement.constant.RoleEnum;
import com.hubproductsmanagement.exception.SecurityMechanismProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration  {

    private final UserConfig userConfig;

    public SecurityConfiguration(UserConfig userConfig) {
        this.userConfig = userConfig;

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(PasswordEncoderConfig.passwordEncoder())
                .withUser(userConfig.getUserAdmin())
                .password(PasswordEncoderConfig.passwordEncoder().encode(userConfig.getPassAdmin()))
                .roles(RoleEnum.ADMINISTRATOR.name()) .and()

                .withUser(userConfig.getUserStaff())
                .password(PasswordEncoderConfig.passwordEncoder().encode(userConfig.getPassStaff()))
                .roles(RoleEnum.STAFF.name());

    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests(requests-> {
            try {
                requests
                        .anyRequest()
                        .authenticated()
                        .and()
                        .httpBasic(Customizer.withDefaults());
            } catch (Exception e) {
                throw new SecurityMechanismProblem("Fatal error! Please contact the administrator!",e);
            }
        });
        return http.build();


    }


    @Configuration
    public class PasswordEncoderConfig  {

        @Bean
        public static PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    }

}

