package com.laker.admin.module.bus.pojo;

import com.laker.admin.constant.CommonConstant;
import com.laker.admin.enums.CollStatusEnum;
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
    private String name;
    @NotNull
    private String type;
    //封面
    private String cover;
    // 简介
    private String introduction;
    // 待发布
    private String status = CollStatusEnum.PENDING_RELEASE_STATUS.getCode();

    // 未删除
    private String isDeleted = CommonConstant.NOT_DELETED;

}
