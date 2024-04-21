package com.laker.admin.module.bus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.FavoritesEntity;
import com.laker.admin.module.bus.mapper.FavoritesMapper;
import com.laker.admin.module.bus.service.FavoritesService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("favoritesService")
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, FavoritesEntity> implements FavoritesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<FavoritesEntity> page = this.page(
                new Query<FavoritesEntity>().getPage(params),
                new QueryWrapper<FavoritesEntity>().eq("creator",userId)
        );

        return new PageUtils(page);
    }

}