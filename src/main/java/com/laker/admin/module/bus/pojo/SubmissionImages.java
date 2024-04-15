package com.laker.admin.module.bus.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnny
 * @Classname SubmissionImages  稿件图片
 * @Description
 * @Date 2024/4/15 13:45
 */
@Data
public class SubmissionImages {
    private Long id;

    @ApiModelProperty(value = "所属稿件")
    private Long submissionId;

    @ApiModelProperty(value = "对应路径")
    private String url;

    @ApiModelProperty(value = "顺序位置")
    private Integer position;



}
