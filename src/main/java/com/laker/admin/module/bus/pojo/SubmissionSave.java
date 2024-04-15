package com.laker.admin.module.bus.pojo;

import com.laker.admin.constant.CommonConstant;
import com.laker.admin.enums.SubmissionStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author johnny
 * @Classname SubmissionSave
 * @Description
 * @Date 2024/4/15 13:02
 */
@Data
public class SubmissionSave {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "简介")
    private String summary;

    @ApiModelProperty(value = "合集id,调用列表拿到,进行传递")
    @NotNull
    private Long collId;

    @ApiModelProperty(value = "合集名称,调用列表拿到,进行传递")
    private String collName;

    @ApiModelProperty(value = "封面 是路径 ")
    private String cover;

    @ApiModelProperty(value = "0-已发布")
    private String status = SubmissionStatusEnum.PUBLISHED_STATUS.getCode();


    @ApiModelProperty(value = "是否删除 0-删除 1- 未删除")
    private String isDeleted = CommonConstant.NOT_DELETED;


    @ApiModelProperty(value = "上传稿件的url,以,隔开")
    private String url;
}
