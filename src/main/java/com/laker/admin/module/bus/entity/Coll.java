package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bus_coll")
public class Coll extends Base{



    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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


}