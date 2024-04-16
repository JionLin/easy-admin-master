package com.laker.admin.module.bus.pojo;

import com.laker.admin.constant.CommonConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author johnny
 * @Classname SubmissionCommentSave
 * @Description
 * @Date 2024/4/16 11:21
 */
@Data
public class SubmissionCommentSave {

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论时间")
    private Date commentTime=new Date();


    @ApiModelProperty(value = "所属稿件id")
    private Long submissionId;

    @ApiModelProperty(value = "所属稿件名称")
    private String submissionName;

    @ApiModelProperty(value = "是否删除 0-删除 1-未删除")
    private String isDeleted= CommonConstant.NOT_DELETED;

}
