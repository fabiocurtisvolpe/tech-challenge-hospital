package com.adjt.agendamento.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilitado para facilitar testes em APIs (REST)
                .authorizeHttpRequests(auth -> auth
                        // 1) Login Público
                        .requestMatchers("/api/login/**", "/api/public/**").permitAll()

                        // 2) Utilizador: Admin, Médico e Paciente podem acessar
                        .requestMatchers("/api/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEDICO", "ROLE_PACIENTE")

                        // 3) Consulta: Médico e Paciente podem acessar
                        .requestMatchers("/api/consulta/**").hasAnyAuthority("ROLE_MEDICO", "ROLE_PACIENTE")

                        // Qualquer outra requisição precisa estar autenticada
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Permite autenticação via Header Basic Auth
                .formLogin(Customizer.withDefaults()); // Habilita tela de ‘login’ padrão se for ‘web’

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
