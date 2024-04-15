package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 稿件图片信息表
 * </p>
 *
 * @author johnny
 * @since 2024-04-15
 */
@ApiModel(value="BusSubmissionImage对象", description="稿件图片信息表")
@Data
@EqualsAndHashCode(callSuper = false)
public class BusSubmissionImage extends Base {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属稿件")
    private Long submissionId;

    @ApiModelProperty(value = "对应路径")
    private String url;

    @ApiModelProperty(value = "顺序位置")
    private Integer position;

    @ApiModelProperty(value = "是否删除 0- 已删除 1- 未删除")
    private String isDeleted;


}
