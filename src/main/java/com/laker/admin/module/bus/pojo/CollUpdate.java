package com.laker.admin.module.bus.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author johnny
 * @Classname CollUpdate
 * @Description
 * @Date 2024/4/14 08:54
 */
@Data
public class CollUpdate {

    @NotNull
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "类型 0-短篇 1-热血 2-情感 3-剧情 4-轻松 传递0/1/2/3/4")
    private String type;
    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "是否删除 0未删除 1 删除")
    private String isDeleted ;
}
