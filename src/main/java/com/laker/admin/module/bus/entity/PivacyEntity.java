package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 业务表_隐私设置
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:04:25
 */
@Data
@TableName("bus_pivacy")
public class PivacyEntity  extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 我的收藏 0-隐藏 1-公开
	 */
	private String myFavorites;
	/**
	 * 我的订阅 0-隐藏 1-公开,默认公开 
	 */
	private String mySubscription;
	/**
	 * 0-删除 1-未删除
	 */
	private String isDeleted;


}
