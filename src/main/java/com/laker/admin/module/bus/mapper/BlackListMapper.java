package com.laker.admin.module.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laker.admin.module.bus.entity.BlackListEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务表_黑名单
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:04:25
 */
@Mapper
public interface BlackListMapper extends BaseMapper<BlackListEntity> {
	
}
