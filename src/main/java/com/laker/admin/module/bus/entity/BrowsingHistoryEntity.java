package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务_浏览记录表
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
@Data
@TableName("bus_browsing_history")
public class BrowsingHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "主键")
	private Long id;

	/**
	 * 合集id
	 */
	@ApiModelProperty(value = "合集id")
	private String collId;

	/**
	 * 合集名称

	 */
	@ApiModelProperty(value = "合集名称 ")
	private String collName;

	/**
	 * 稿件id
	 */
	@ApiModelProperty(value = "稿件id")
	private String submissionId;

	/**
	 * 稿件名称
	 */
	@ApiModelProperty(value = "稿件名称")
	private String submissionName;

	/**
	 * 字典状态
	 */
	@ApiModelProperty(value = "字典状态")
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
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private Long updater;

}
