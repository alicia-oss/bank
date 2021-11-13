package com.bank.controller;

import com.bank.lang.Result;
import com.bank.pojo.LoginModel;
import com.bank.pojo.User;
import com.bank.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/bank/login")
public class LoginController {

  @Autowired
  UserService userService;
//
//  @PostMapping("/login")
//  public Result<?> login(@RequestBody LoginModel loginModel,
//                         HttpSession session){
//    String account = loginModel.getAccount();
//    String  password = loginModel.getPassword();
//    System.out.println(account + password);
//    String captcha = loginModel.getCaptcha();
//
////    1.校验验证码
//
////    2.校验用户名
//    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//    lambdaQueryWrapper.eq(User::getAccount,account);
//    User user = userService.getOneUser(lambdaQueryWrapper);
//    System.out.println(user);
//    if(user == null ){
//      return Result.error("登陆失败，该用户不存在!");
//    }
//    else if(!user.getPassword().equals(password)){
//      return Result.error("登录失败，密码错误!");
//    }
//    else {
//      //3.在rides中删除验证，在session中添加标记
//      session.setAttribute("userInfo", user);
//      return Result.OK("登录成功！");
//    }
//  }
//

  @PostMapping("/success")
  public Result<?> loginSuccess( ){
    return Result.OK("登录成功！");
  }

  @PostMapping("/fail")
  public Result<?> loginFail(){
    return Result.error("登录失败！");
  }

  @GetMapping("/logout")
  public Result<?> logout(HttpSession session){
    session.removeAttribute("userInfo");
    return Result.OK();
  }

  @GetMapping("/getInfo")
  public Result<?> getInfo(HttpSession session){
    User user =(User) session.getAttribute("userInfo");
    return Result.OK(user);
  }


}
