package com.laker.admin.module.bus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.laker.admin.module.bus.entity.Coll;
import com.laker.admin.module.bus.pojo.CollCalculation;

/**
 * @author johnny
 * @Classname CollService
 * @Description
 * @Date 2024/4/13 12:15
 */
public interface CollService extends IService<Coll> {
    int updateColl(CollCalculation collCalculation);
}
