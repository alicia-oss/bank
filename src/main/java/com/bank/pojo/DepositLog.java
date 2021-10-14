package com.bank.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
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
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DepositLog对象", description="")
public class DepositLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "PK")
      @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "FK 储户id")
    private String customerId;

    @ApiModelProperty(value = "金额")
    private Integer amount;

    @ApiModelProperty(value = "数据字典 存取款类型")
    private Integer type;

      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String uploadUserId;

    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "上传人姓名")
    @TableField(exist = false)
    private String uploadUserName;

    @ApiModelProperty(value = "储户姓名")
    @TableField(exist = false)
    private String realname;

    @ApiModelProperty(value = "储户电话号码")
    @TableField(exist = false)
    private String phone;


}
