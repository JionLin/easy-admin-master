package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 业务_我的收藏
 * 
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:04:25
 */
@Data
@TableName("bus_favorites")
public class FavoritesEntity extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 单话作品名字
	 */
	private String oneShotName;
	/**
	 * 单话作品id
	 */
	private Long oneShotId;
	/**
	 * 0-删除 1-未删除

	 */
	private String isDeleted;


}
