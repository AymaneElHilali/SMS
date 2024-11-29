package com.aymaneelhilali.SMS.configration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuritéConfig {

    // we d'ont want to use the defaulte filterChain we want to make ours one
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

        http
//                disable csrf
                .csrf(off -> off.disable())

                .authorizeHttpRequests(req -> req.requestMatchers("/"));


        return http.build();

    }
}
