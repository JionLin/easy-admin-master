package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务表_隐私设置
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
@Data
@TableName("bus_pivacy")
public class PivacyEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "")
	private Long id;

	/**
	 * 我的收藏 0-隐藏 1-公开
	 */
	@ApiModelProperty(value = "我的收藏 0-隐藏 1-公开")
	private String myFavorites;

	/**
	 * 我的订阅 0-隐藏 1-公开,默认公开 
	 */
	@ApiModelProperty(value = "我的订阅 0-隐藏 1-公开,默认公开 ")
	private String mySubscription;

	/**
	 * 0-删除 1-未删除
	 */
	@ApiModelProperty(value = "0-删除 1-未删除")
	private String isDeleted;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private Long creator;

	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
	private Long updater;

}
