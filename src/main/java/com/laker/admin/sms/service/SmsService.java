package com.laker.admin.sms.service;


import com.laker.admin.sms.util.Result;

public interface SmsService {
    /**
     * 获取手机验证码
     *
     * @param phone
     * @return
     */
    Result sendSmsCode(String phone);

    /**
     * 手机验证码验证
     *
     * @param phone
     * @param code
     * @return
     */
    boolean validSmsCode(String phone, String code);
}
