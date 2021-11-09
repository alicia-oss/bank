package com.bank.mapper;

import com.bank.pojo.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-01
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

  //分页查询
  IPage<User> selectPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

  //按id查询
  User selectId(String id);



  User getByAccount(String account);
}
