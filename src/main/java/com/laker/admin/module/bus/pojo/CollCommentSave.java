package com.laker.admin.module.bus.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author johnny
 * @Classname CollCommentSave
 * @Description
 * @Date 2024/4/16 09:36
 */
@Data
public class CollCommentSave {


    @ApiModelProperty(value = "父id,第一级别默认为0,其余级别默认为当前评论上一级的id.")
    private Long parentId = 0L;

    @ApiModelProperty(value = "评论内容,暂时允许中文,不允许图片")
    private String content;

    @ApiModelProperty(value = "评论时间,当前时间")
    private Date commentTime = new Date();


    @ApiModelProperty(value = "合集id")
    @NotNull
    private Long collId;

    @ApiModelProperty(value = "合集名字")
    @NotNull
    private String collName;

    @ApiModelProperty(value = "是否删除 0-删除 1- 未删除")
    private String isDeleted = "1";

}
