package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.PivacyEntity;
import com.laker.admin.module.bus.service.PivacyService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/pivacy")
public class PivacyController {
    @Autowired
    private PivacyService pivacyService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表,page(页数 默认为1),limit(一页显示的数量 默认为10)")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pivacyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据单个id获取信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PivacyEntity pivacy = pivacyService.getById(id);

        return R.ok().put("pivacy", pivacy);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody PivacyEntity pivacy){
		pivacyService.save(pivacy);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody PivacyEntity pivacy){
		pivacyService.updateById(pivacy);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		pivacyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
