package com.laker.admin.module.bus.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class coll {

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