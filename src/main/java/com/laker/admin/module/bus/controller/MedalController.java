package com.laker.admin.module.bus.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.laker.admin.module.bus.entity.MedalEntity;
import com.laker.admin.module.bus.service.MedalService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import com.laker.admin.utils.UserAndDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;




/**
 * 业务表_勋章表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:40:03
 */
@Api(tags = "业务表_勋章表")
@RestController
@RequestMapping("/medal")
public class MedalController {
    @Autowired
    private MedalService medalService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表,page(页数 默认为1),limit(一页显示的数量 默认为10)")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = medalService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据单个id获取信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MedalEntity medal = medalService.getById(id);

        return R.ok().put("medal", medal);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody MedalEntity medal){
        UserAndDateUtil.setCreateUserInfoAndDate(medal);
		medalService.save(medal);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody MedalEntity medal){
        medal.setCreator(StpUtil.getLoginIdAsLong());
        UserAndDateUtil.setUpdateUserInfoAndDate(medal);
		medalService.updateById(medal);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		medalService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
