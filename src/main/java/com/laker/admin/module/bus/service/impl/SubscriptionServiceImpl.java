package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.SubscriptionEntity;
import com.laker.admin.module.bus.mapper.SubscriptionMapper;
import com.laker.admin.module.bus.service.SubscriptionService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("subscriptionService")
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, SubscriptionEntity> implements SubscriptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SubscriptionEntity> page = this.page(
                new Query<SubscriptionEntity>().getPage(params),
                new QueryWrapper<SubscriptionEntity>()
        );

        return new PageUtils(page);
    }

}