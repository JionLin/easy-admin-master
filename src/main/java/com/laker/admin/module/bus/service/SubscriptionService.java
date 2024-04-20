package com.laker.admin.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laker.admin.module.bus.entity.SubscriptionEntity;
import com.laker.admin.utils.PageUtils;

import java.util.Map;

/**
 * 业务表_订阅表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
public interface SubscriptionService extends IService<SubscriptionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

