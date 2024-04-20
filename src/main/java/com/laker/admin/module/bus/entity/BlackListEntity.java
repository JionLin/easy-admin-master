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
 * 业务表_黑名单
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:25:39
 */
@Data
@TableName("bus_black_list")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BlackListEntity extends Base implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 解除限制 0-解除(删除)-不可以查看  1-未解除(不删除) 可以查看
     */
    @ApiModelProperty(value = "解除限制 0-解除(删除)-不可以查看  1-未解除(不删除) 可以查看 ")
    @TableLogic
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
