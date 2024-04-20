package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.MedalEntity;
import com.laker.admin.module.bus.service.MedalService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务表_勋章表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
@RestController
@RequestMapping("bus/medal")
public class MedalController {
    @Autowired
    private MedalService medalService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = medalService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MedalEntity medal = medalService.getById(id);

        return R.ok().put("medal", medal);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MedalEntity medal) {
        medalService.save(medal);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MedalEntity medal) {
        medalService.updateById(medal);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        medalService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
