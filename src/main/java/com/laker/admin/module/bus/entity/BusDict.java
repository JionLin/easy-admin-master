package com.laker.admin.module.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 业务字典表
 * </p>
 *
 * @author johnny
 * @since 2024-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BusDict对象", description="业务字典表")
public class BusDict extends Base {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典名称 和字典数据是一对,字典编码 设置唯一 ")
    private String dictName;

    @ApiModelProperty(value = "字典描述")
    private String description;

    @ApiModelProperty(value = "字典数据")
    private String dictData;

    @ApiModelProperty(value = "字典状态")
    private String isDeleted;


}
