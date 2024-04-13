package com.laker.admin.module.bus.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author johnny
 * @Classname collSave
 * @Description
 * @Date 2024/4/13 12:12
 */
@Data
public class CollSave {

    private Long id;


    private String name;


    private String type;


    private String cover;

    private String introduction;

    private String status;


    private Long favoritesCount;

    private Long readCount;

    private Long monthlyTicketCount;


    private Long commentsCount;


    private Long complaintsCount;


    private Long sharesCount;


    private String isDeleted;


    private Date createTime;


    private Date updateTime;


    private String creator;


    private String updater;
}
