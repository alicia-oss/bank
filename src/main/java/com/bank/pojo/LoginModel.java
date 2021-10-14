package com.bank.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="登录对象", description="登录对象")
public class LoginModel {
  @ApiModelProperty(value = "账号")
  private String account;
  @ApiModelProperty(value = "密码")
  private String password;
  @ApiModelProperty(value = "验证码")
  private String captcha;
  @ApiModelProperty(value = "验证码key")
  private String checkKey;
}
