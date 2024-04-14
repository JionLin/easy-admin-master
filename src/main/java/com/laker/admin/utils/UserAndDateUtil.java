package com.laker.admin.utils;

import com.laker.admin.framework.ext.mybatis.UserInfoAndPowers;
import com.laker.admin.framework.utils.EasyAdminSecurityUtils;
import com.laker.admin.module.bus.entity.Base;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author johnny
 * @Classname UserAndDateUtil
 * @Description
 * @Date 2024/4/14 07:41
 */
@Component
public class UserAndDateUtil {


    // 创建的时候,获取当前用户的用户id 和创建时间以及修改时间
    public static void setCreateUserInfoAndDate(Base base) {
        UserInfoAndPowers currentUserInfo = EasyAdminSecurityUtils.getCurrentUserInfo();
        Long userId = currentUserInfo.getUserId();
        base.setUpdater(userId);
        base.setCreator(userId);
        base.setCreateTime(new Date());
        base.setUpdateTime(new Date());
    }

    // 修改的时候,获取当前用户的修改时间和修改人,都使用userId
    public static void setUpdateUserInfoAndDate(Base base) {
        UserInfoAndPowers currentUserInfo = EasyAdminSecurityUtils.getCurrentUserInfo();
        Long userId = currentUserInfo.getUserId();
        base.setUpdater(userId);
        base.setUpdateTime(new Date());
    }
}
