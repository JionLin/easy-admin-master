package com.laker.admin.module.bus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.PrivacyEntity;
import com.laker.admin.module.bus.mapper.PrivacyMapper;
import com.laker.admin.module.bus.service.PrivacyService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.Query;
import com.laker.admin.utils.UserAndDateUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("pivacyService")
public class PrivacyServiceImpl extends ServiceImpl<PrivacyMapper, PrivacyEntity> implements PrivacyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<PrivacyEntity> page = this.page(
                new Query<PrivacyEntity>().getPage(params),
                new QueryWrapper<PrivacyEntity>().eq("creator",userId)
        );

        return new PageUtils(page);
    }

    /**
     * 获取隐私设置参数
     * @return
     */
    @Override
    public PrivacyEntity info() {
        PrivacyMapper baseMapper = this.baseMapper;
        long userId = StpUtil.getLoginIdAsLong();
        PrivacyEntity privacy = baseMapper.selectOne(
                new QueryWrapper<PrivacyEntity>().eq("creator", userId));
        if (privacy == null) {
            privacy = new PrivacyEntity();
            UserAndDateUtil.setCreateUserInfoAndDate(privacy);
            baseMapper.insert(privacy);
        }
        return privacy;
    }

}