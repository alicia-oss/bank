package com.bank.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "PK 用户id")
      @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "账户")
    @TableField(condition = SqlCondition.LIKE_RIGHT)
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    @TableField(condition = SqlCondition.LIKE)
    private String name;

    @ApiModelProperty(value = "职能（系统中的） 来自数据字典role")
    private Integer role;

    @ApiModelProperty(value = "工号")
    private String employeeId;

    @ApiModelProperty(value = "FK 上传人id")
    private String uploadUserId;

    @ApiModelProperty(value = "上传人姓名")
    @TableField(exist = false)
    private String uploadUserName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private String permission;


}
