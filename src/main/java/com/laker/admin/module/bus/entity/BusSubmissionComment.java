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
 * 稿件评论表
 * </p>
 *
 * @author johnny
 * @since 2024-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BusSubmissionComment对象", description="稿件评论表")
public class BusSubmissionComment extends Base {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论时间")
    private Date commentTime;

    @ApiModelProperty(value = "评论点赞数")
    private Long commentLikes;

    @ApiModelProperty(value = "所属稿件id")
    private Long submissionId;

    @ApiModelProperty(value = "所属稿件名称")
    private String submissionName;

    @ApiModelProperty(value = "是否删除 0-删除 1-未删除")
    private String isDeleted;



}
