package com.bank.service.impl;

import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
  @Autowired
  UserService userService;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    userService.getByAccount(s);

    return null;
  }
}
