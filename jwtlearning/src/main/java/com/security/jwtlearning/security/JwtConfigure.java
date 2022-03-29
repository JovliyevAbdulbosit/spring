package com.security.jwtlearning.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    private final JwtTokenProvayder jwtTokenProvayder;

    public JwtConfigure(JwtTokenProvayder jwtTokenProvayder) {
        this.jwtTokenProvayder = jwtTokenProvayder;
    }
    @Override
    public void configure(HttpSecurity httpSecurity){
        JwtTokenFilter jwtTokenFilter=new JwtTokenFilter(jwtTokenProvayder);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
