package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class Coll {



    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
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