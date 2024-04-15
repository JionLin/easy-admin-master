package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.module.bus.mapper.BusSubmissionMapper;
import com.laker.admin.module.bus.entity.BusSubmission;
import com.laker.admin.module.bus.service.BusSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    @Autowired
    private BusSubmissionMapper submissionMapper;
    /**
     * 根据合集id 查询稿件列表
     * @param collId
     * @return
     */
    @Override
    public List<BusSubmission> selectListByCollId(Long collId) {
        List<BusSubmission> submissionList = submissionMapper.selectList(new QueryWrapper<BusSubmission>().eq("coll_id", collId)
                        .eq("is_deleted", CommonConstant.NOT_DELETED));
        return submissionList;
    }
}
