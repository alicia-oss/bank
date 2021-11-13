package com.bank.service;

import com.bank.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 使命必达
 * @since 2021-11-09
 */
public interface UserRoleService extends IService<UserRole> {

  List<String> getRoleListById(String id);

}
