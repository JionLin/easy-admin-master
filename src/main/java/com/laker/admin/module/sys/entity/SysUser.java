package com.laker.admin.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author laker
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "部门id")
    private Long deptId;
    @TableField(exist = false)
    private SysDept dept;
    @TableField(exist = false)
    private String deptName;
    @ApiModelProperty(value = "1-男 2-女")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    private Integer enable;
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


    @TableField(exist = false)
    private String roleIds;


    /**
     * 社交账号-微信
     */
    @ApiModelProperty(value = "社交账号-微信")
    private String socialAccountWx;
    /**
     * 我的签名
     */
    @ApiModelProperty(value = "我的签名")
    private String signature;
}
