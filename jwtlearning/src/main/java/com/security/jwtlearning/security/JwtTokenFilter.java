package com.security.jwtlearning.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvayder jwtTokenProvayder;

    public JwtTokenFilter(JwtTokenProvayder jwtTokenProvayder) {
        this.jwtTokenProvayder = jwtTokenProvayder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String token =jwtTokenProvayder.resolveToken((HttpServletRequest) request);
    if (token!= null && jwtTokenProvayder.validityToken(token)){
        Authentication authentication = jwtTokenProvayder.getAuthentication(token);
        if (authentication!=null){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
    chain.doFilter(request,response);
    }
}
