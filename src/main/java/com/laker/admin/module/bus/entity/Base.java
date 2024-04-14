package com.laker.admin.module.bus.entity;

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


    private Date createTime;


    private Date updateTime;


    private Long creator;


    private Long updater;
}
