package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.PivacyEntity;
import com.laker.admin.module.bus.service.PivacyService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务表_隐私设置
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
@RestController
@RequestMapping("bus/pivacy")
public class PivacyController {
    @Autowired
    private PivacyService pivacyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = pivacyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        PivacyEntity pivacy = pivacyService.getById(id);

        return R.ok().put("pivacy", pivacy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PivacyEntity pivacy) {
        pivacyService.save(pivacy);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PivacyEntity pivacy) {
        pivacyService.updateById(pivacy);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        pivacyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
