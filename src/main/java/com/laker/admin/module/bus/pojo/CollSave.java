package com.laker.admin.module.bus.pojo;

import com.laker.admin.constant.CommonConstant;
import com.laker.admin.enums.CollStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author johnny
 * @Classname collSave
 * @Description
 * @Date 2024/4/13 12:12
 */
@Data
public class CollSave {


    @NotNull
    @ApiModelProperty(value = "名字")
    private String name;

    @NotNull
    @ApiModelProperty(value = "类型 0-短篇 1-热血 2-情感 3-剧情 4-轻松 传递0/1/2/3/4")
    private String type;
    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "状态 10-待发布 11-已发布 12-审核中 13-未通过 默认待发布")
    private String status = CollStatusEnum.PENDING_RELEASE_STATUS.getCode();

    @ApiModelProperty(value = "是否删除 1未删除")
    private String isDeleted = CommonConstant.NOT_DELETED;

}
