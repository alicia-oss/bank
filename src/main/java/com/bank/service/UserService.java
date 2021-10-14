package com.bank.service;

import com.bank.pojo.Customer;
import com.bank.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-01
 */
public interface UserService extends IService<User> {

 User selectId(String id);


}
