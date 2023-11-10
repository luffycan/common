package com.luffycan.commonutils.controller;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.function.Function;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/06 8:19
 */
@SpringBootConfiguration
public class WebSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

//    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("luffycan").password("123456").authorities("admin")
                .passwordEncoder(s -> new BCryptPasswordEncoder().encode(s)).build());
        return inMemoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //基于token，所以不需要csrf防护
        httpSecurity.csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //登录注册不需要认证
                .antMatchers("/user/login", "/user/register").permitAll()
                //除上面的所有请求全部需要鉴权认证
                .anyRequest()
                .authenticated();
        //禁用缓存
        httpSecurity.headers().cacheControl();
        httpSecurity.exceptionHandling().authenticationEntryPoint(new MyUnauthorizedHandler())
                .accessDeniedHandler(new MyAccessDeniedHandler());

        //将我们的JWT filter添加到UsernamePasswordAuthenticationFilter前面，因为这个Filter是authentication开始的filter，我们要早于它
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }
}
