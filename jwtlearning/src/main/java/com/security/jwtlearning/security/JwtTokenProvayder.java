package com.security.jwtlearning.security;

import com.security.jwtlearning.domen.RoleUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
@Component
public class JwtTokenProvayder {
    @Value("${jwt.token.secred}")
    private String secret;

    @Value("${jwt.token.validity}")
    private long validityMilliySecond;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvayder(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return   new BCryptPasswordEncoder();

    }
    @PostConstruct
    protected void inti(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToke(String username , Set<RoleUserEntity> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles" , roles);
        Date now = new Date();

        Date validity = new Date(now.getTime() + validityMilliySecond);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256 , secret)
                .compact();

    }
    //token muddatini tekshiradi
    public boolean validityToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        if(claimsJws.getBody().getExpiration().before(new Date())){
            return false;
        }
        return true;
    }
    //tokeni ajratib oladi
    public String resolveToken(HttpServletRequest request){
        String bearerToken =request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
    // ro'yxatdan o'tganmi yoqligini teksiradi
    public Authentication getAuthentication(String token){
        UserDetails user = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user , "" ,user.getAuthorities());
    }
    //usernameni qaytaradi
    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

}
