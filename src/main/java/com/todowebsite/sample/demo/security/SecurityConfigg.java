package com.todowebsite.sample.demo.security;

import com.todowebsite.sample.demo.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;

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
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define a query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(

                "select username, password, active from users where username=?"
        );


        //define a query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(

                "select username, role from users where username=?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cfg -> cfg.configurationSource(corsPermitAllConfigurationSource()));


        http.authorizeHttpRequests(configurer ->

                        configurer

                                .requestMatchers(HttpMethod.GET, "/home").hasRole("USERS")
                                .requestMatchers(HttpMethod.GET, "/home/**").hasRole("USERS")
                                .requestMatchers(HttpMethod.POST, "/home").hasRole("USERS")
                                .requestMatchers(HttpMethod.POST, "/home/**").hasRole("USERS")
                                .requestMatchers(HttpMethod.PUT, "/home").hasRole("USERS")
                                .requestMatchers(HttpMethod.PUT, "/home/**").hasRole("USERS")





                                .requestMatchers("/accepted", "/register").permitAll()

                                .anyRequest().authenticated()

                                                //.requestMatchers("/accepted", "/register").permitAll()
                                                //.requestMatchers("/home").authenticated()
                                               //.requestMatchers("/register").permitAll()
        )
                .formLogin(form ->
                        form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/authenticatingUser")
                        .permitAll()



                );

        return http.build();

    }


    protected void configure(HttpSecurity httpSecurity) throws Exception {

    }

}
