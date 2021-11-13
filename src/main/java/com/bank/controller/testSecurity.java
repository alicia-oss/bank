package com.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/security")
public class testSecurity {
  @GetMapping(value = "test11")
  public String test11(){
    return "1-1";
  }

  @GetMapping(value = "test12")
  public String test12(){
    return "1-2";
  }

  @GetMapping(value = "test21")
  public String test21(){
    return "2-1";
  }
  @GetMapping(value = "test22")
  public String test22(){
    return "2-2";
  }
  @GetMapping(value = "test31")
  public String test31(){
    return "3-1";
  }
  @GetMapping(value = "test32")
  public String test32(){
    return "3-2";
  }

}
