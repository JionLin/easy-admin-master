package com.laker.admin.module.sms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信实体
 */
@Data
public class Sms implements Serializable {
    private String phone;   //电话号码
    private String code;    //短信验证码
    private Long time;      //短信验证码生成时间

    public Sms() {
    }

    public Sms(String phone, String code, Long time) {
        this.phone = phone;
        this.code = code;
        this.time = time;
    }


}
