package com.laker.admin.mapper;

import com.laker.admin.module.bus.entity.coll;

public interface collMapper {

    int deleteByPrimaryKey(Long id);

    int insert(coll record);


    int insertSelective(coll record);

    coll selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(coll record);


    int updateByPrimaryKey(coll record);
}