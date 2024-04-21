package com.laker.admin.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laker.admin.module.bus.entity.PrivacyEntity;
import com.laker.admin.utils.PageUtils;

import java.util.Map;

/**
 * 业务表_隐私设置
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
public interface PrivacyService extends IService<PrivacyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PrivacyEntity info();
}

