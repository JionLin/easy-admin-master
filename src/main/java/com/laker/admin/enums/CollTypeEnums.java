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
public enum CollTypeEnums {


    // 类型  0- 短篇 1-热血 2-情感 3-剧情 4- 轻松
    SHORT_STORY_TYPE("0", "短篇"),
    PASSIONATE_TYPE("1", "热血"),
    EMOTIONAL_TYPE("2", "情感"),
    DRAMATIC_TYPE("3", "剧情"),
    relaxing_TYPE("4", "轻松");




    private  String code;
    private  String value;

    /**
     * 判断code是否存在
     */
    public static boolean isExist(String code) {
        return parse(code) != null;
    }

    /**
     * 根据code获取枚举
     */
    public static CollTypeEnums parse(String code) {
        for (CollTypeEnums codeEnum : CollTypeEnums.values()) {
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
        CollTypeEnums codeEnum = parse(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getValue();
        }
    }
}
