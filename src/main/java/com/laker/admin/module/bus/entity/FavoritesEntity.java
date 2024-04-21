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
 * 业务_我的收藏
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
@Data
@TableName("bus_favorites")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FavoritesEntity  extends  Base implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "")
	private Long id;

	/**
	 * 单话作品名字
	 */
	@ApiModelProperty(value = "单话作品名字")
	private String oneShotName;

	/**
	 * 单话作品id
	 */
	@ApiModelProperty(value = "单话作品id")
	private Long oneShotId;

	/**
	 * 0-删除 1-未删除

	 */
	@ApiModelProperty(value = "0-未删除 1-删除 ")
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

}
