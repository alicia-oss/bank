package com.bank.controller;


import com.bank.lang.Result;
import com.bank.pojo.DepositLog;
import com.bank.pojo.User;
import com.bank.service.DepositLogService;
import com.bank.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/bank/deposit-log")
public class DepositLogController {

  @Autowired
  DepositLogService depositLogService;

  @Autowired
  UserService userService;

  @ApiOperation(value="记录管理-分页列表查询", notes="记录管理-分页列表查询")
  @GetMapping(value = "/list")
  public Result<?> queryLogList( DepositLog depositLog,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize
  ){
    LambdaQueryWrapper<DepositLog> queryWrapper = new LambdaQueryWrapper<>();
    if(depositLog.getCustomerId() != null){
      queryWrapper.eq(DepositLog::getCustomerId,depositLog.getCustomerId());
    }
    Page<DepositLog> page = new Page<>(pageNo,pageSize);
    IPage<DepositLog> pageList = depositLogService.page(page, queryWrapper);
    pageList.getRecords().forEach((item)->{
      User temp = userService.getById(item.getUploadUserId());
      item.setUploadUserName(temp.getName()+"-"+temp.getEmployeeId());
    });
    return Result.OK(pageList);
  }

}

