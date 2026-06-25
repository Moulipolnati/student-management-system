package com.mouli.studentmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mouli.studentmanagementsystem.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

            	    // Public APIs
            	    .requestMatchers(
            	            "/auth/**",
            	            "/swagger-ui/**",
            	            "/swagger-ui.html",
            	            "/v3/api-docs/**")
            	    .permitAll()

            	    // Student APIs
            	    .requestMatchers(
            	            "/students",
            	            "/students/page",
            	            "/students/search",
            	            "/students/*")
            	    .hasAnyRole("ADMIN", "USER")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.POST,
            	            "/students")
            	    .hasRole("ADMIN")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.PUT,
            	            "/students/*")
            	    .hasRole("ADMIN")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.DELETE,
            	            "/students/*")
            	    .hasRole("ADMIN")

            	    // Course APIs
            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.GET,
            	            "/courses/**")
            	    .hasAnyRole("ADMIN", "USER")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.POST,
            	            "/courses/**")
            	    .hasRole("ADMIN")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.PUT,
            	            "/courses/**")
            	    .hasRole("ADMIN")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.DELETE,
            	            "/courses/**")
            	    .hasRole("ADMIN")

            	    // Enrollment APIs
            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.GET,
            	            "/enrollments/**")
            	    .hasAnyRole("ADMIN", "USER")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.POST,
            	            "/enrollments/**")
            	    .hasRole("ADMIN")

            	    .requestMatchers(
            	            org.springframework.http.HttpMethod.DELETE,
            	            "/enrollments/**")
            	    .hasRole("ADMIN")

            	    .anyRequest()
            	    .authenticated())

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}