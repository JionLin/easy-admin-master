package com.laker.admin.module.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.module.bus.entity.BusSubmissionImage;
import com.laker.admin.module.bus.mapper.BusSubmissionImageMapper;
import com.laker.admin.module.bus.service.BusSubmissionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 稿件图片信息表 服务实现类
 * </p>
 *
 * @author johnny
 * @since 2024-04-15
 */
@Service
@Transactional
public class BusSubmissionImageServiceImpl extends ServiceImpl<BusSubmissionImageMapper, BusSubmissionImage> implements BusSubmissionImageService {

    @Autowired
    private BusSubmissionImageMapper imageMapper;

    public static final Integer MIN = 4;
    public static final Integer MAX = 99;

    @Override
    public List<BusSubmissionImage> getSubmissionId(Integer id) {
        List<BusSubmissionImage> submissionImages = imageMapper.selectList(new QueryWrapper<BusSubmissionImage>().eq("submission_id", id)
                .eq("is_deleted", CommonConstant.NOT_DELETED));
        return submissionImages;
    }

}
