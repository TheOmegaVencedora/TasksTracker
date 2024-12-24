package com.todowebsite.sample.demo.security;

import com.todowebsite.sample.demo.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfigg {

    UserDao userDao;


    private CorsConfigurationSource corsPermitAllConfigurationSource(){
        return (request) -> {
            CorsConfiguration config = new CorsConfiguration();
            config.applyPermitDefaultValues();
            return config;
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cfg -> cfg.configurationSource(corsPermitAllConfigurationSource()));


        http.authorizeHttpRequests(configurer -> configurer

                                                .requestMatchers("/accepted").permitAll()
                                                .requestMatchers("/home").authenticated()
                                                .requestMatchers("/register").permitAll().anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()



                );

        return http.build();

    }

}
