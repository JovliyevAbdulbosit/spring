package com.security.jwtlearning.config;

import com.security.jwtlearning.security.JwtConfigure;
import com.security.jwtlearning.security.JwtTokenProvayder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.JeeConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class Cunfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvayder jwtTokenProvayder;

    public Cunfiguration(@Lazy UserDetailsService userDetailsService, JwtTokenProvayder jwtTokenProvayder) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvayder = jwtTokenProvayder;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();}

//@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
//               .and()
//              .withUser("user").password(passwordEncoder().encode("user123")).roles("USER");
//}
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/register/").permitAll()

                .antMatchers("/user/test").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigure(jwtTokenProvayder));
    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
