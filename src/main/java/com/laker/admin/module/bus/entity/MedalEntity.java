package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务表_勋章表
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
@Data
@TableName("bus_medal")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MedalEntity  extends  Base implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "")
	private Long id;

	/**
	 * 勋章数量
	 */
	@ApiModelProperty(value = "勋章获取获取数量-粉丝数")
	private Long quantity;


	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称-粉丝等级")
	private String name;

	/**
	 * 0-删除 1-未删除
	 */
	@ApiModelProperty(value = "0-删除 1-未删除")
	@TableLogic
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

	// TODO 获取粉丝数和勋章名称

}
