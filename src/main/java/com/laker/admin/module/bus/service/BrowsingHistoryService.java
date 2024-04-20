package com.laker.admin.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laker.admin.module.bus.entity.BrowsingHistoryEntity;
import com.laker.admin.utils.PageUtils;

import java.util.Map;

/**
 * 业务_浏览记录表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
public interface BrowsingHistoryService extends IService<BrowsingHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

