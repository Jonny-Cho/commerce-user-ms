package com.commerce.security;

import com.commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final Environment env;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 권한 관련 configure
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests().antMatchers("/**")
//            .hasIpAddress("222.108.238.150")
            .permitAll()
            .and()
            .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
        return authenticationFilter;
    }

    // 인증 관련 configure
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
        super.configure(auth);
    }
}
