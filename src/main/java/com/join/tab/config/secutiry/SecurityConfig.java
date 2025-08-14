package com.join.tab.config.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public AuthenticationManager authManager(
        HttpSecurity http,
        PasswordEncoder passwordEncoder,
        UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            // public page
                .requestMatchers(
      "/app/auth/**",
                    "/app/about-us",
                    "/app/contact",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/assets/**",
                    "/app/storage",
                    "/app/cart"
                    ).permitAll()
                // admin page
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/app/auth/sign-in")
                .defaultSuccessUrl("/app/dashboard", true)
                .failureUrl("/app/auth/sign-in?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/app/dashboard")
                .permitAll()
        );
           
        return http.build();
    }



}
