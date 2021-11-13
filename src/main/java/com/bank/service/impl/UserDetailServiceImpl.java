package com.bank.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.pojo.User;
import com.bank.service.UserRoleService;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  UserService userService;

  @Autowired
  UserRoleService userRoleService;
  @Override
  public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
    if(StrUtil.isEmpty(account)){
      throw new UsernameNotFoundException("账号为空!");
    }
    User user = userService.getByAccount(account);
    if(user == null){
      throw new UsernameNotFoundException("账号不存在!");
    }
    List<GrantedAuthority> list = new ArrayList<>();
    user.getRoles().forEach((item)->{
      list.add(new SimpleGrantedAuthority(item));
    });
    return new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassword(),list);
  }
}
