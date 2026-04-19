package com.security.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity
              .csrf(csrf -> csrf.disable())
              .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(auth -> auth
              .requestMatchers("/public/**",
                                "/auth/signup**",
                                "/auth/login**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**")
                              .permitAll()
                              .anyRequest().authenticated()

              )
        // .formLogin(Customizer.withDefaults());
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
         return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
