package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.module.bus.mapper.BusSubmissionMapper;
import com.laker.admin.module.bus.entity.BusSubmission;
import com.laker.admin.module.bus.service.BusSubmissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 稿件表 服务实现类
 * </p>
 *
 * @author johnny
 * @since 2024-04-15
 */
@Service
@Transactional
public class BusSubmissionServiceImpl extends ServiceImpl<BusSubmissionMapper, BusSubmission> implements BusSubmissionService {

}
