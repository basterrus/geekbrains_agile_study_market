package com.geekbrains.shop.configs;

public class SecurityEndpoints {

    // permit_all
    static final String[] AUTH_WHITELIST_SWAGGER = {
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


    // permit_all
     static final String[] AUTH_WHITELIST_AUTH = {
            "/api/v1/unsecured/**",
            "/api/v1/register/**",
            "/api/v1/login/**"
    };

    // permit_all
     static final String[] AUTH_WHITELIST_PRODUCT = {
            "/api/v1/products/**"
    };

    // hasRole("ADMIN")
     static final String[] AUTH_REQUIRE_ADMIN_ROLE = {
            "/api/v1/users/**"
    };

     static final String[] AUTH_WHITELIST_CARTS ={
             "/api/v1/cart"
     };

     static final String[] AUTH_WHITELIST_CATEGORIES ={
             "/api/v1/categories"
     };


     // hasRole("MANAGER")
     static final String[] AUTH_REQUIRE_MANAGER_ROLE = {
    };
}
