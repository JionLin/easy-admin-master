package com.laker.admin.module.bus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.BrowsingHistoryEntity;
import com.laker.admin.module.bus.mapper.BrowsingHistoryMapper;
import com.laker.admin.module.bus.service.BrowsingHistoryService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("browsingHistoryService")
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistoryEntity> implements BrowsingHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<BrowsingHistoryEntity> page = this.page(
                new Query<BrowsingHistoryEntity>().getPage(params),
                new QueryWrapper<BrowsingHistoryEntity>().eq("creator",userId).orderByDesc("create_time")
        );

        return new PageUtils(page);
    }

}