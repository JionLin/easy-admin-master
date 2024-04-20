package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.BlackListEntity;
import com.laker.admin.module.bus.service.BlackListService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务表_黑名单
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
@RestController
@Api(tags = "业务表_黑名单")
@RequestMapping("bus/blacklist")
public class BlackListController {
    @Autowired
    private BlackListService blackListService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = blackListService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "单个查询")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        BlackListEntity blackList = blackListService.getById(id);

        return R.ok().put("blackList", blackList);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody BlackListEntity blackList) {
        blackListService.save(blackList);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody BlackListEntity blackList) {
        blackListService.updateById(blackList);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        blackListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
