package com.laker.admin.module.bus.pojo;

import lombok.Data;


// 合集 计算数
@Data
public class CollCalculation {


    private Long id;

    private Long favoritesCount;

    private Long readCount;

    private Long monthlyTicketCount;

    private Long commentsCount;

    private Long complaintsCount;

    private Long sharesCount;
}