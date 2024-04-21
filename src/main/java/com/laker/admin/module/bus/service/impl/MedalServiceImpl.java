package com.laker.admin.module.bus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.MedalEntity;
import com.laker.admin.module.bus.mapper.MedalMapper;
import com.laker.admin.module.bus.service.MedalService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("medalService")
public class MedalServiceImpl extends ServiceImpl<MedalMapper, MedalEntity> implements MedalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<MedalEntity> page = this.page(
                new Query<MedalEntity>().getPage(params),
                new QueryWrapper<MedalEntity>().eq("creator",userId)
        );

        return new PageUtils(page);
    }

}