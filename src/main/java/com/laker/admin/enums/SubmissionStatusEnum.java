package com.laker.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author johnny
 * @Classname SubmissionStatusEnum
 * @Description
 * @Date 2024/4/15 13:04
 */
@Getter
@AllArgsConstructor
public enum SubmissionStatusEnum {

    // 状态  0-已发布 1-待审核 2-未通过
    PUBLISHED_STATUS("0", "已发布"),
    UNDER_REVIEW_STATUS("1", "审核中"),
    NOT_APPROVED_STATUS("2", "未通过");


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
    public static SubmissionStatusEnum parse(String code) {
        for (SubmissionStatusEnum codeEnum : SubmissionStatusEnum.values()) {
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
        SubmissionStatusEnum codeEnum = parse(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getValue();
        }
    }
}
