package com.laker.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author johnny
 * @Classname CollStatusEnum
 * @Description
 * @Date 2024/4/13 16:09
 */
@Getter
@AllArgsConstructor
public enum CollStatusEnum {

    // 状态   10- 待发布 11-已发布 12-审核中 13-未通过
    PENDING_RELEASE_STATUS("10", "待发布"),
    PUBLISHED_STATUS("11", "已发布"),
    UNDER_REVIEW_STATUS("12", "审核中"),
    NOT_APPROVED_STATUS("13", "未通过");


    public String code;
    public String value;

    /**
     * 判断code是否存在
     */
    public static boolean isExist(String code) {
        return parse(code) != null;
    }

    /**
     * 根据code获取枚举
     */
    public static CollStatusEnum parse(String code) {
        for (CollStatusEnum codeEnum : CollStatusEnum.values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    /**
     * 根据code获取name
     */
    public static String getEnumValue(String code) {
        CollStatusEnum codeEnum = parse(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getValue();
        }
    }
}
