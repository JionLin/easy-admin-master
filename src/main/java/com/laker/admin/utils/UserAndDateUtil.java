package com.laker.admin.utils;

import cn.dev33.satoken.stp.StpUtil;
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

    /**
     *  // UserInfoAndPowers currentUserInfo = EasyAdminSecurityUtils.getCurrentUserInfo();
     *   // Long userId = currentUserInfo.getUserId();
     * @param base
     */
    public static void setCreateUserInfoAndDate(Base base) {
        Long userId = StpUtil.getLoginIdAsLong();
        base.setUpdater(userId);
        base.setCreator(userId);
        base.setCreateTime(new Date());
        base.setUpdateTime(new Date());
    }

    // 修改的时候,获取当前用户的修改时间和修改人,都使用userId
    public static void setUpdateUserInfoAndDate(Base base) {
        Long userId = StpUtil.getLoginIdAsLong();
        base.setUpdater(userId);
        base.setUpdateTime(new Date());
    }
}
