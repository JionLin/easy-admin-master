package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.BlackListEntity;
import com.laker.admin.module.bus.mapper.BlackListMapper;
import com.laker.admin.module.bus.service.BlackListService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("blackListService")
public class BlackListServiceImpl extends ServiceImpl<BlackListMapper, BlackListEntity> implements BlackListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlackListEntity> page = this.page(
                new Query<BlackListEntity>().getPage(params),
                new QueryWrapper<BlackListEntity>()
        );

        return new PageUtils(page);
    }

}