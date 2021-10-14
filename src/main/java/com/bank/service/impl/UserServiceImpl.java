package com.bank.service.impl;

import com.bank.pojo.User;
import com.bank.mapper.UserMapper;
import com.bank.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User selectId(String id) {
    return userMapper.selectId(id);
  }
}