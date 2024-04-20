package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.SubscriptionEntity;
import com.laker.admin.module.bus.service.SubscriptionService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务表_订阅表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
@RestController
@RequestMapping("bus/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = subscriptionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SubscriptionEntity subscription = subscriptionService.getById(id);

        return R.ok().put("subscription", subscription);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SubscriptionEntity subscription) {
        subscriptionService.save(subscription);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SubscriptionEntity subscription) {
        subscriptionService.updateById(subscription);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        subscriptionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
