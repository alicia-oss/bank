package com.bank.interceptor;

import com.bank.lang.CommonConstant;
import com.bank.pojo.User;
import lombok.val;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("userInfo");
    if(user == null ){
      System.out.println("未登录");
      response.sendError(CommonConstant.SC_NO_LOGIN, "登录失效，请重新登录");
      return false;
    }
    else {
      System.out.println("拦截日志"+user.getName());
      return true;
    }
  }
}
