package com.laker.admin.module.bus.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.laker.admin.module.bus.entity.PrivacyEntity;
import com.laker.admin.module.bus.service.PrivacyService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import com.laker.admin.utils.UserAndDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;




/**
 * 业务表_隐私设置
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:40:03
 */
@Api(tags = "业务表_隐私设置")
@RestController
@RequestMapping("/privacy")
public class PrivacyController {
    @Autowired
    private PrivacyService privacyService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表,page(页数 默认为1),limit(一页显示的数量 默认为10)")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = privacyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据当前用户获取隐私设置,如果为空,默认设置我的收藏和我的订阅为 隐藏")
    @GetMapping("/info")
    public R info() {
        PrivacyEntity privacy = privacyService.info();
        return R.ok().put("privacy", privacy);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody PrivacyEntity pivacy){
        UserAndDateUtil.setCreateUserInfoAndDate(pivacy);
		privacyService.save(pivacy);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody @Valid PrivacyEntity pivacy){
        pivacy.setCreator(StpUtil.getLoginIdAsLong());
        UserAndDateUtil.setUpdateUserInfoAndDate(pivacy);
		privacyService.updateById(pivacy);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    // @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		privacyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
