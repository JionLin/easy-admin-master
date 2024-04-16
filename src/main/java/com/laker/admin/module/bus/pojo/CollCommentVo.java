package com.laker.admin.module.bus.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author johnny
 * @Classname CollCommentVo
 * @Description
 * @Date 2024/4/16 10:09
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class CollCommentVo {

    @ApiModelProperty(value = "主键id")
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


    @ApiModelProperty(value = "子集合")
    private List<CollCommentVo> children;
}
