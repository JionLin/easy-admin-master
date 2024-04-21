package com.laker.admin.module.sys.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnny
 * @Classname PhoneDto
 * @Description
 * @Date 2024/4/21 11:50
 */
@Data
public class PhoneDto {

    @ApiModelProperty(value = "旧手机号码")
    private String oldPhone;
    @ApiModelProperty(value = "短信验证码")
    private String smsVerCode;
    @ApiModelProperty(value = "新手机号码")
    private String phone;
}
