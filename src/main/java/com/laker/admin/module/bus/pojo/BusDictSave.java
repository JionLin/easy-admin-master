package com.laker.admin.module.bus.pojo;

import com.laker.admin.constant.CommonConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnny
 * @Classname BusDictSave
 * @Description
 * @Date 2024/4/16 12:49
 */
@Data
public class BusDictSave {

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典描述")
    private String description;

    @ApiModelProperty(value = "字典数据")
    private String dictData;

    @ApiModelProperty(value = "字典状态")
    private String isDeleted= CommonConstant.NOT_DELETED;

}
