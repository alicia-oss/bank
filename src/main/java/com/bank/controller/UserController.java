package com.bank.controller;
import com.bank.lang.CommonConstant;
import com.bank.lang.Result;
import com.bank.pojo.User;
import com.bank.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-01
 */

@RestController
@RequestMapping("/bank/user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   *
   * @param user
   * @param pageNo
   * @param pageSize
   * @return
   */
  @ApiOperation(value="用户管理-分页列表查询", notes="用户管理-分页列表查询")
  @GetMapping(value = "/list")
  public Result<?> queryUserList(User user,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize
                              ){
    System.out.println(user);
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    Page<User> page = new Page<User>(pageNo,pageSize);
    IPage<User> pageList = userService.page(page, queryWrapper);
    pageList.getRecords().forEach((item)->{
      User temp = userService.getById(item.getUploadUserId());
      item.setUploadUserName(temp.getName()+"-"+temp.getEmployeeId());
    });
    System.out.println(pageList);
    return Result.OK(pageList);
  }

  @ApiOperation(value="用户管理-添加", notes="用户管理-添加")
  @PostMapping(value = "/add")
  public Result<?> add(@RequestBody User user, HttpSession session) {
    if(addCheck(user))
    {
      User upload = (User) session.getAttribute("UserInfo");
      Date date = new Date(System.currentTimeMillis());
      user.setUploadUserId(upload.getId());
      user.setCreateTime(date);
      user.setUpdateTime(date);
      //密码加密
      String  salt = new SecureRandomNumberGenerator().toString();
      System.out.println("盐==="+salt);
//      设置hash算法迭代次数,加密
      String password =new SimpleHash("md5",user.getEmployeeId(),salt,CommonConstant.HASH_TIME).toString();
      user.setPassword(password);
      userService.save(user);
      return Result.OK("添加成功！");
    }
    else{
      return Result.error("添加失败，账号或工号已被使用！");
    }
  }

  @ApiOperation(value="用户管理-编辑", notes="用户管理-编辑")
  @PutMapping(value = "/edit")
  public Result<?> edit(@RequestBody User user) {
    Date date = new Date(System.currentTimeMillis());
    user.setUpdateTime(date);
    userService.updateById(user);
    return Result.OK("编辑成功!");
  }


  @ApiOperation(value="用户管理-通过id删除", notes="用户管理-通过id删除")
  @DeleteMapping(value = "/delete")
  public Result<?> delete(@RequestParam(name="id",required=true) String id) {
    userService.removeById(id);
    return Result.OK("删除成功!");
  }

  /**
   *
   * @param ids
   * @return
   */
  @ApiOperation(value="用户管理-批量删除", notes="用户管理-批量删除")
  @DeleteMapping(value = "/deleteBatch")
  public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
    this.userService.removeByIds(Arrays.asList(ids.split(",")));
    return Result.OK("批量删除成功!");
  }

  @ApiOperation(value="事故报告表-通过id查询", notes="事故报告表-通过id查询")
  @GetMapping(value = "/queryById")
  public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
     User user = userService.getById(id);
    if(user == null) {
      return Result.error("未找到对应数据");
    }
    return Result.OK(user);
  }

  @ApiOperation(value="用户管理-校验", notes="用户管理-校验")
  @GetMapping(value = "/check")
  public Result<?> check(HttpServletRequest req){
    Map<String, String[]> parameterMap = req.getParameterMap();
    QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
      String mapKey = entry.getKey();
      String mapValue = entry.getValue()[0];
      queryWrapper.eq(mapKey, mapValue).or();
    }
    if(userService.list(queryWrapper).size() > 0){
      return Result.error("该值已被使用！");
    }
    return Result.OK();
  }


  public boolean addCheck(User user){
    QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    queryWrapper.eq("account", user.getAccount())
            .or()
            .eq("employee_id", user.getEmployeeId());
    if(userService.list(queryWrapper).size() > 0){
      return false;
    }
    return true;
  }


}

