package com.geekbrains.shop.configs;

import com.geekbrains.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static com.geekbrains.shop.configs.SecurityEndpoints.*;

@Configuration
//@EnableWebSecurity(debug = true) //FIXME - убрать debug
@RequiredArgsConstructor
@Slf4j
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    public SecurityFilterChain securityFilterChain(JwtRequestFilter filter, HttpSecurity httpSecurity) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        return httpSecurity
                .csrf(CsrfConfigurer::disable)
                // TODO - cors сконфигурировать
                .cors(Customizer.withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handler -> handler.accessDeniedHandler((request, response, accessDeniedException) -> {
                    log.debug("Access denied: {}", accessDeniedException.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                }))
                .exceptionHandling(handler -> handler.authenticationEntryPoint((request, response, authException) -> {
                    log.debug("Authentication error: {}", authException.getMessage());
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                }))


                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/secured").authenticated())   //test
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_WHITELIST_SWAGGER).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_WHITELIST_AUTH).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_WHITELIST_PRODUCT).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_REQUIRE_ADMIN_ROLE).hasRole("ADMIN")) // [/api/v1/users]
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/admin-auth").hasAnyRole("ADMIN")) //test
                .authorizeHttpRequests(req -> req.anyRequest().permitAll())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(applicationContext.getBean(UserService.class));

        return daoAuthenticationProvider;
    }


}
