package com.bank.service;

import com.bank.pojo.Customer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-08
 */
public interface CustomerService extends IService<Customer> {

  IPage<Customer> mySelectPage(Page<Customer> page,QueryWrapper<Customer> queryWrapper);

  void putMoney(String id,int amount);
  void getMoney(String id,int amount);
}
