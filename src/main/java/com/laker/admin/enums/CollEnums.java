package com.laker.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author johnny
 * @Classname CollEnums
 * @Description
 * @Date 2024/4/13 11:27
 */

@Getter
@AllArgsConstructor
public enum CollEnums {


    // 类型  0- 短篇 1-热血 2-情感 3-剧情 4- 轻松
    SHORT_STORY_TYPE("0", "短篇"),
    PASSIONATE_TYPE("1", "热血"),
    EMOTIONAL_TYPE("2", "情感"),
    DRAMATIC_TYPE("3", "剧情"),
    relaxing_TYPE("4", "轻松"),

    // 状态   10- 待发布 11-已发布 12-审核中 13-未通过
    PENDING_RELEASE_STATUS("10", "待发布"),
    PUBLISHED_STATUS("11", "已发布"),
    UNDER_REVIEW_STATUS("12", "审核中"),
    NOT_APPROVED_STATUS("13", "未通过");


    private final String type;
    private final String desc;
}
