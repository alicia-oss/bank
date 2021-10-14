package com.bank.controller;


import com.bank.lang.Result;
import com.bank.pojo.Customer;
import com.bank.pojo.DepositLog;
import com.bank.pojo.User;
import com.bank.service.CustomerService;
import com.bank.service.DepositLogService;
import com.bank.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-08
 */
@RestController
@RequestMapping("/bank/customer")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @Autowired
  UserService userService;

  @Autowired
  DepositLogService depositLogService;

  @ApiOperation(value="储户管理-分页列表查询", notes="储户管理-分页列表查询")
  @GetMapping(value = "/list")
  public Result<?> queryUserList(Customer customer,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize
  ){
    QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>(customer);
    Page<Customer> page = new Page<Customer>(pageNo,pageSize);
    IPage<Customer> pageList = customerService.page(page,queryWrapper);
    pageList.getRecords().forEach(item->{
      User temp = userService.getById(item.getUploadUserId());
      item.setPassword("");
      item.setUploadUserName(temp.getName()+"-"+temp.getEmployeeId());
    });
    pageList.getRecords().forEach(System.out::println);
    return Result.OK(pageList);
  }

  @ApiOperation(value="储户管理-添加", notes="储户管理-添加")
  @PostMapping(value = "/add")
  public Result<?> add(@RequestBody Customer customer, HttpSession session) {
    if(check(customer)){
      User uploader =(User) session.getAttribute("userInfo");
      customer.setUploadUserId(uploader.getId());
      customer.setBalance(0);
      customerService.save(customer);
      return Result.OK("添加成功");
    }
    else {
      return Result.error("该身份证已经注册过储户");
    }
  }

  @ApiOperation(value="储户管理-编辑", notes="储户管理-编辑")
  @PutMapping(value = "/edit")
  public Result<?> edit(@RequestBody Customer customer) {
    customerService.updateById(customer);
    return Result.OK("编辑成功!");
  }

  @ApiOperation(value="储户管理-通过id删除", notes="储户管理-通过id删除")
  @DeleteMapping(value = "/delete")
  public Result<?> delete(@RequestParam(name="id",required=true) String id) {
    customerService.removeById(id);
    return Result.OK("删除成功!");
  }

  /**
   *
   * @param ids
   * @return
   */
  @ApiOperation(value="储户管理-批量删除", notes="储户管理-批量删除")
  @DeleteMapping(value = "/deleteBatch")
  public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
    this.customerService.removeByIds(Arrays.asList(ids.split(",")));
    return Result.OK("批量删除成功!");
  }

  @ApiOperation(value="储户管理-存款", notes="储户管理-存款")
  @PostMapping(value = "/put")
  public Result<?> putMoney(@RequestParam(name = "id",required = true) String id,
                            @RequestParam(name = "type",required = true) int type,
                            @RequestParam(name = "amount",required = true) int amount,
                            @RequestParam(name = "password",required = true) String password,
                            HttpSession session) {
    //1.验证密码
    //2.加钱
    //3.创建日志
    Customer customer = customerService.getById(id);
    if(customer == null || !customer.getPassword().equals(password)){
      return Result.error("密码错误!");
    }
    else{
      User uploader =(User) session.getAttribute("userInfo");
      customerService.putMoney(id,amount);
      DepositLog depositLog = new DepositLog();
      depositLog.setCustomerId(id).setAmount(amount).setType(type).setUploadUserId(uploader.getId());
      System.out.println(depositLog);
      depositLogService.save(depositLog);
      return Result.OK("存款成功");
    }
  }

  @ApiOperation(value="储户管理-取款", notes="储户管理-取款")
  @PostMapping(value = "/get")
  public Result<?> getMoney(@RequestParam(name = "id",required = true) String id,
                            @RequestParam(name = "type",required = true) int type,
                            @RequestParam(name = "amount",required = true) int amount,
                            @RequestParam(name = "password",required = true) String password,
                            HttpSession session) {
    //1.验证密码
    //2.加钱
    //3.创建日志
    Customer customer = customerService.getById(id);
    if(customer == null || !customer.getPassword().equals(password) ){
      return Result.error("密码错误!");
    }
    else if(customer.getBalance() < amount){
      return Result.error("余额不足！");
    }
    else{
      User uploader =(User) session.getAttribute("userInfo");
      customerService.getMoney(id,amount);
      DepositLog depositLog = new DepositLog();
      depositLog.setCustomerId(id).setAmount(amount).setType(type).setUploadUserId(uploader.getId());
      System.out.println(depositLog);
      depositLogService.save(depositLog);
      return Result.OK("取款成功");
    }
  }

  public  boolean check(Customer customer){
    LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Customer::getIdentityCard,customer.getIdentityCard());
    if(customerService.list(queryWrapper).size() > 0){
      return false;
    }
    else {
      return true;
    }
  }

}

