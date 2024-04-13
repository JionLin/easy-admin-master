package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.entity.Coll;
import com.laker.admin.module.bus.mapper.CollMapper;
import com.laker.admin.module.bus.service.CollService;
import org.springframework.stereotype.Service;

/**
 * @author johnny
 * @Classname CollServiceImpl
 * @Description
 * @Date 2024/4/13 12:15
 */
@Service
public class CollServiceImpl extends ServiceImpl<CollMapper, Coll> implements CollService {
}
