package com.bank.service.impl;

import com.bank.mapper.UserMapper;
import com.bank.pojo.UserRole;
import com.bank.mapper.UserRoleMapper;
import com.bank.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 使命必达
 * @since 2021-11-09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

  @Autowired
  UserRoleMapper userRoleMapper;
  @Override
  public List<String> getRoleListById(String id) {
    return userRoleMapper.getListByAccount(id);
  }
}
