package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.PivacyEntity;
import com.laker.admin.module.bus.mapper.PivacyMapper;
import com.laker.admin.module.bus.service.PivacyService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("pivacyService")
public class PivacyServiceImpl extends ServiceImpl<PivacyMapper, PivacyEntity> implements PivacyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PivacyEntity> page = this.page(
                new Query<PivacyEntity>().getPage(params),
                new QueryWrapper<PivacyEntity>()
        );

        return new PageUtils(page);
    }

}