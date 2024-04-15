package com.laker.admin.module.bus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.laker.admin.module.bus.entity.BusSubmissionImage;

import java.util.List;

/**
 * <p>
 * 稿件图片信息表 服务类
 * </p>
 *
 * @author johnny
 * @since 2024-04-15
 */
public interface BusSubmissionImageService extends IService<BusSubmissionImage> {

    List<BusSubmissionImage> getSubmissionId(Integer id);

}
