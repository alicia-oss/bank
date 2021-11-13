package com.bank.auth;

import com.bank.lang.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailHandler extends AuthenticJSON implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    Result result;
    if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
       result = Result.error("用户名或密码错误");
    } else if (exception instanceof DisabledException) {
       result = Result.error("用户无权限");
    } else {
       result = Result.error("登录失败");
    }
    this.writeJSON(httpServletRequest, httpServletResponse, result);
  }
}
