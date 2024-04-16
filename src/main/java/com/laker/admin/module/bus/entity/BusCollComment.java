package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 合集评论表
 * </p>
 *
 * @author johnny
 * @since 2024-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BusCollComment对象", description="合集评论表")
public class BusCollComment  extends Base{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id,第一级别默认为0,其余级别默认为当前评论上一级的id.")
    private Long parentId;

    @ApiModelProperty(value = "评论内容,暂时允许中文,不允许图片")
    private String content;

    @ApiModelProperty(value = "评论时间")
    private Date commentTime;

    @ApiModelProperty(value = "评论点赞数")
    private Long commentLikes;

    @ApiModelProperty(value = "合集id")
    private Long collId;

    @ApiModelProperty(value = "合集名字")
    private String collName;

    @ApiModelProperty(value = "是否删除 0-删除 1-不删除")
    private String isDeleted;






}
