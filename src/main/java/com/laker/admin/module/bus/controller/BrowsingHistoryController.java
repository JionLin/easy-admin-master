package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.BrowsingHistoryEntity;
import com.laker.admin.module.bus.service.BrowsingHistoryService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务_浏览记录表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
@RestController
@Api(tags = "业务_浏览记录")
@RequestMapping("bus/browsinghistory")
public class BrowsingHistoryController {
    @Autowired
    private BrowsingHistoryService browsingHistoryService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = browsingHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "单个信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        BrowsingHistoryEntity browsingHistory = browsingHistoryService.getById(id);

        return R.ok().put("browsingHistory", browsingHistory);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody BrowsingHistoryEntity browsingHistory) {
        browsingHistoryService.save(browsingHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody BrowsingHistoryEntity browsingHistory) {
        browsingHistoryService.updateById(browsingHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        browsingHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
