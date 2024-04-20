package com.laker.admin.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laker.admin.module.bus.entity.PivacyEntity;
import com.laker.admin.utils.PageUtils;

import java.util.Map;

/**
 * 业务表_隐私设置
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
public interface PivacyService extends IService<PivacyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

