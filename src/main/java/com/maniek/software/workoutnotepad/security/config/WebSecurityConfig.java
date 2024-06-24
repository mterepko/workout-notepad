package com.maniek.software.workoutnotepad.security.config;

import com.maniek.software.workoutnotepad.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserService UserService;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register")
                        .permitAll()
                        .requestMatchers("/css/**")
                        .permitAll()
                        .requestMatchers("/images/**")
                        .permitAll()
                        .requestMatchers("/register/success")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                                .defaultSuccessUrl("/", true)
                )
                .logout(logout -> logout.permitAll()
                )
                .authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(BCryptPasswordEncoder);
        provider.setUserDetailsService(UserService);
        return provider;
    }
}
