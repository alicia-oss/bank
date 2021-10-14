package com.bank.service.impl;

import com.bank.pojo.Customer;
import com.bank.mapper.CustomerMapper;
import com.bank.service.CustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-08
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

  @Autowired
  CustomerMapper customerMapper;
  @Override
  public IPage<Customer> mySelectPage(Page<Customer> page, QueryWrapper<Customer> queryWrapper) {
    return customerMapper.mySelectPage(page, queryWrapper);
  }

  @Override
  public void  putMoney(String id,int amount){
    Customer customer = getById(id);
    customer.setBalance(customer.getBalance()+amount);
    updateById(customer);
  }

  @Override
  public void  getMoney(String id,int amount){
    Customer customer = getById(id);
    customer.setBalance(customer.getBalance()-amount);
    updateById(customer);
  }


}
