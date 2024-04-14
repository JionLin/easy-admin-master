package com.laker.admin.module.bus.pojo;

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
    private Long id;

    private String name;

    private String type;
    //封面
    private String cover;
    // 简介
    private String introduction;

    // 未删除
    private String isDeleted;
}
