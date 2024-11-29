package com.aymaneelhilali.SMS.configration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuritéConfig {

    // we d'ont want to use the defaulte filterChain we want to make ours one
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

        http
//                disable csrf
                .csrf(off -> off.disable())
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/**").authenticated())
                .authorizeHttpRequests(req -> req.requestMatchers("/h2-console/**").permitAll())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();

    }
}
