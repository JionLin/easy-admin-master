package com.laker.admin.module.bus.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author johnny
 * @Classname SubmissionUpdate
 * @Description
 * @Date 2024/4/15 14:10
 */
@Data
public class SubmissionUpdate {

    private Long id;

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

    @ApiModelProperty(value = "上传稿件的url,以,隔开")
    private List<SubmissionImages> submissionImages;
}
