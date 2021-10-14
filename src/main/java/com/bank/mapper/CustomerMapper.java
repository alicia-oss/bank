package com.bank.mapper;

import com.bank.pojo.Customer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2021-10-08
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

 IPage<Customer> mySelectPage(Page<Customer> page, @Param(Constants.WRAPPER) QueryWrapper<Customer> queryWrapper);

}
