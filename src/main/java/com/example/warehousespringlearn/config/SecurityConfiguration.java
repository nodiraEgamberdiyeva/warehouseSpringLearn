package com.example.warehousespringlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("director").password(passwordEncoder().encode("director")).roles("DIRECTOR").authorities("ALL")
                .and()
                .withUser("accountant").password(passwordEncoder().encode("accountant")).roles("ACCOUNTANT")
                .authorities("ADD_CURRENCY", "READ_CURRENCY", "READ_ALL_CURRENCY", "EDIT_CURRENCY", "DELETE_CURRENCY")
                .and()
                .withUser("WManager").password(passwordEncoder().encode("WManager")).roles("W_MANAGER")
                .authorities("ADD_PRODUCT", "READ_PRODUCT", "READ_ALL_PRODUCT", "EDIT_PRODUCT", "DELETE_PRODUCT",
                        "ADD_SUPPLIER", "READ_SUPPLIER", "READ_ALL_SUPPLIER", "EDIT_SUPPLIER", "DELETE_SUPPLIER",
                        "ADD_OUTPUT_PRODUCT", "READ_OUTPUT_PRODUCT", "READ_ALL_OUTPUT_PRODUCT", "EDIT_OUTPUT_PRODUCT", "DELETE_OUTPUT_PRODUCT",
                        "ADD_OUTPUT", "READ_OUTPUT", "READ_ALL_OUTPUT", "EDIT_OUTPUT", "DELETE_OUTPUT",
                        "ADD_INPUT_PRODUCT", "READ_INPUT_PRODUCT", "READ_ALL_INPUT_PRODUCT", "EDIT_INPUT_PRODUCT", "DELETE_INPUT_PRODUCT",
                        "ADD_INPUT", "READ_INPUT", "READ_ALL_INPUT", "EDIT_INPUT", "DELETE_INPUT",
                        "ADD_CLIENT", "READ_CLIENT", "READ_ALL_CLIENT", "EDIT_CLIENT", "DELETE_CLIENT",
                        "ADD_CURRENCY", "READ_CURRENCY", "READ_ALL_CURRENCY", "EDIT_CURRENCY", "DELETE_CURRENCY",
                        "ADD_ATTACHMENT", "READ_ATTACHMENT", "READ_ALL_ATTACHMENT", "EDIT_ATTACHMENT", "DELETE_ATTACHMENT",
                        "ADD_CATEGORY", "READ_CATEGORY", "READ_ALL_CATEGORY", "EDIT_CATEGORY", "DELETE_CATEGORY");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
