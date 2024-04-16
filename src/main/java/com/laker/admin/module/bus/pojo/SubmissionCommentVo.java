package com.laker.admin.module.bus.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author johnny
 * @Classname SubmissionCommentVo
 * @Description
 * @Date 2024/4/16 11:30
 */
@Data
public class SubmissionCommentVo {


    @ApiModelProperty(value = "主键id")
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

    @ApiModelProperty("子节点")
    private List<SubmissionCommentVo> children;


}
