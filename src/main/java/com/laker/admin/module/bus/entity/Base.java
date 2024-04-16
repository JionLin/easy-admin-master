package com.laker.admin.module.bus.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author johnny
 * @Classname Base
 * @Description base 基类
 * @Date 2024/4/14 07:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Base {

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private Long creator;

    @ApiModelProperty(value = "更新人")
    private Long updater;
}
