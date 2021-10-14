package com.bank.config;


import com.bank.pojo.User;
import com.bank.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
  @Autowired
  private UserService userService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    SimpleAuthenticationInfo temp = new SimpleAuthenticationInfo();
    return (AuthorizationInfo) temp;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    String id = authenticationToken.getPrincipal().toString();
    User user = userService.getById(id);
    String passwordInDB = user.getPassword();
    String salt = user.getSalt();
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(id, passwordInDB, ByteSource.Util.bytes(salt), getName());
    return authenticationInfo;
  }
}
