package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 稿件表
 * </p>
 *
 * @author johnny
 * @since 2024-04-15
 */
@ApiModel(value="BusSubmission对象", description="稿件表")
@Data
@EqualsAndHashCode(callSuper = false)
public class BusSubmission extends Base {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
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

    @ApiModelProperty(value = "进行展示多少话")
    private String numberOfChapter;

    @ApiModelProperty(value = "阅读数")
    private Long readCount;

    @ApiModelProperty(value = "月票数")
    private Long monthlyTicketCount;

    @ApiModelProperty(value = "收藏数")
    private Long favoritesCount;

    @ApiModelProperty(value = "吐槽数")
    private Long complaintsCount;

    @ApiModelProperty(value = "分享数")
    private Long sharesCount;

    @ApiModelProperty(value = "是否删除 0-删除 1- 未删除")
    private String isDeleted;



}
