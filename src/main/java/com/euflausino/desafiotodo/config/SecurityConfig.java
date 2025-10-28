package com.euflausino.desafiotodo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable) //desabilita a proteção de csrf por que o token já é a proteção.
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )// habilita políticas de sessão (stateless JWT, por ex.)
                 .formLogin(AbstractHttpConfigurer::disable)// desabilita form login
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                //definindo autorizacao endpoint (posso ter endpoints publicos)
                .build(); //retorna o filtro de segurança
    }

}
