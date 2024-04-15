package com.laker.admin.module.bus.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author johnny
 * @Classname BusSubmissionItem
 * @Description
 * @Date 2024/4/15 13:42
 */
@Data
public class BusSubmissionItem {
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "简介")
    private String summary;

    @ApiModelProperty(value = "合集id")
    private Long collId;

    @ApiModelProperty(value = "合集名称")
    private String collName;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "0-已发布 1-待审核 2-未通过")
    private String status;


    @ApiModelProperty(value = "是否删除 0-删除 1- 未删除")
    private String isDeleted;

    @ApiModelProperty(value = "稿件图片列表")
    private List<SubmissionImages> submissionImages;

}
