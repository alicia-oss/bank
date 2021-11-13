package com.bank.mapper;

import com.bank.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 使命必达
 * @since 2021-11-09
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

  List<String> getListByAccount(String id);
}
