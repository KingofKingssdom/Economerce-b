package com.caNhan.E_conomy.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/auth/admin/login").hasRole("ADMIN")
                        .requestMatchers("/api/product/**").permitAll()
                        .requestMatchers("/api/productColor/**").permitAll()
                        .requestMatchers("/api/productVariant/**").permitAll()
                        .requestMatchers("/api/productSpecification/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/api/brand/**").permitAll()
                        .requestMatchers("/api/category/**").permitAll()
                        .requestMatchers("/api/specificationDetail/**").permitAll()
                        .requestMatchers("/api/cart/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/cartItem/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/order/**").permitAll()
                        .requestMatchers("/api/orderItem/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/api/VNPay/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sử dụng session
                .formLogin(form -> form.disable());
        return httpSecurity.build();
    }

   @Bean
    public AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration
   ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
   }

   @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider (
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
   ) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return  provider;
   }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
//                "https://g814wts5-3000.asse.devtunnels.ms",
//                "https://g814wts5-8080.asse.devtunnels.ms"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // cho phép cookie
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


