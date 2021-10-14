package com.bank.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * <p>
 *
 * </p>
 *
 * @author 使命必达
 * @since 2021-10-08
 */
@Alias("Customer")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Customer对象", description="")
public class Customer implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "PK")
      @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "余额")
    private Integer balance;

    @ApiModelProperty(value = "储户姓名")
    @TableField(condition = SqlCondition.LIKE_RIGHT)
    private String realname;

    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "身份证号码")
    private String identityCard;

    @ApiModelProperty(value = "密码")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "上传人id")
    private String uploadUserId;

    @ApiModelProperty(value = "上传人姓名")
    @TableField(exist = false)
    private String uploadUserName;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
