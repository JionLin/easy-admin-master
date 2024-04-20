package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 业务表_黑名单
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:04:25
 */
@Data
@TableName("bus_black_list")
public class BlackListEntity extends Base implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id" )
    private Long id;
    /**
     * 解除限制 0-未解除 1-解除
     */
    @ApiModelProperty(value = "解除限制 0-未解除 1-解除")
    private String isDeleted;
    /**
     * 屏蔽用户id
     */
    @ApiModelProperty(value = "屏蔽用户id")
    private Long blockUserId;
    /**
     * 屏蔽用户名称
     */
    @ApiModelProperty(value = "屏蔽用户名称")
    private String blockUserName;


}
