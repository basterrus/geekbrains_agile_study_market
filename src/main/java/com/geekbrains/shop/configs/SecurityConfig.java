package com.geekbrains.shop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity(debug = true) //FIXME - убрать debug
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST_SWAGGER={
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private static final String[] AUTH_WHITELIST_AUTH = {
            "/api/v1/unsecured/**",
            "/api/v1/register/**",
            "/api/v1/login/**"
    };

    private static final String[] AUTH_WHITELIST_PRODUCT={
            "/api/v1/products/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(JwtRequestFilter filter, HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .csrf(CsrfConfigurer::disable)
                // TODO - cors сконфигурировать
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                })

                //TODO - вынести endpoints в константы
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/secured").authenticated())   //test
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_WHITELIST_SWAGGER).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_WHITELIST_AUTH).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(AUTH_WHITELIST_PRODUCT).permitAll())
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
        return new StandardAuthenticationProvider();
    }


}
