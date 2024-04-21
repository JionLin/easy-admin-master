package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laker.admin.valid.ListValue;
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
@TableName("bus_privacy")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PrivacyEntity extends  Base implements Serializable {
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
	@ListValue(vals = {"0","1"})
	private String myFavorites;

	/**
	 * 我的订阅 0-隐藏 1-公开,默认公开 
	 */
	@ApiModelProperty(value = "我的订阅 0-隐藏 1-公开,默认公开 ")
	@ListValue(vals = {"0","1"})
	private String mySubscription;



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
