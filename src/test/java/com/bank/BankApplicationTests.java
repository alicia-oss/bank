package com.bank;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bank.controller.CustomerController;
import com.bank.pojo.Customer;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class BankApplicationTests {

  @Test
  public void testJWT(){
    HashMap<String,Object> map = new HashMap<>();
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND,20);
    String token = JWT.create()
            .withHeader(map) //可以不设定，就是使用默认的
            .withClaim("userId",20)//payload  //自定义用户名
            .withClaim("username","zhangsan")
            .withExpiresAt(instance.getTime()) //指定令牌过期时间
            .sign(Algorithm.HMAC256("fdahuifeuw78921"));//签名
    System.out.println(token);
  }
}
