package com.bank.config;

import com.bank.auth.MyAuthenticationFailHandler;
import com.bank.auth.MyAuthenticationSuccessHandler;
import com.bank.lang.Result;
import com.bank.service.impl.UserDetailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

  @Autowired
  MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
  @Autowired
  MyAuthenticationFailHandler authenticationFailHandler;
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Autowired
  UserDetailServiceImpl userDetailService;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService)
            .passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.httpBasic()
//            .and()
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated();
//    httpSecurity
//                // 开始认证
//                .authorizeRequests()
//                // 对静态文件和登陆页面放行
//                .antMatchers("/user/**","/a/chaeak").permitAll()
//                .antMatchers("/login.html").permitAll()
//                // 其他请求需要认证登陆
//                .anyRequest().authenticated();
    // post
    http.csrf().disable()
            .cors()
            .and()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .successHandler(myAuthenticationSuccessHandler)
            .failureHandler(authenticationFailHandler)
            .permitAll();
//            .successForwardUrl("/bank/login/success")
//            .failureForwardUrl("/bank/login/failure");
//    http.authorizeRequests()
//            .anyRequest()
//            .authenticated();
//    http.formLogin()
//            .usernameParameter("account") //默认的用户名的参数 account
//            .passwordParameter("password")
//            .successHandler(myAuthenticationSuccessHandler)
//            .failureHandler(authenticationFailHandler)
//            .permitAll();
//
//    http.csrf()
//            .disable();


    // with an HTTP

//    http.formLogin()
//            .usernameParameter("account")
//            .passwordParameter("password")
//            .permitAll()
//            .successHandler((HttpServletRequest request, HttpServletResponse response, Authentication auth)->{
//              Result result = Result.OK();
//              response.setContentType("text/json;charset=utf-8");
//              response.getWriter().write(new ObjectMapper().writeValueAsString(result) );
//            });
  }
}
