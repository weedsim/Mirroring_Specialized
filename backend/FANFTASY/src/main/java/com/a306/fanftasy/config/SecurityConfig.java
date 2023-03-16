package com.a306.fanftasy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()//Form 로그인 인증 기능이 작동함
                .loginPage("/login.html")//사용자 정의 로그인 페이지
                .defaultSuccessUrl("/home")//로그인 성공 후 이동 페이지
                .failureUrl("/login.html?error=true")// 로그인 실패 후 이동 페이지
                .usernameParameter("username")//아이디 파라미터명 설정
                .passwordParameter("password")//패스워드 파라미터명 설정
                .loginProcessingUrl("/login")//로그인 Form Action Url
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication:: "+ authentication.getName());
                        response.sendRedirect("/");//성공하면 메인페이지
                    }
                })//로그인 성공 후 핸들러
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse response,
                                                        AuthenticationException e) throws IOException, ServletException {
                        System.out.println("exception:: "+e.getMessage());
                        response.sendRedirect("/login");//실패하면 로그인 페이지
                    }
                })//로그인 실패 후 핸들러
							.permitAll(); //사용자 정의 로그인 페이지 접근 권한 승인
    }
}
