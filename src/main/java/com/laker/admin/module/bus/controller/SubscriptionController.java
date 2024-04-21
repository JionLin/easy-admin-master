package com.laker.admin.module.bus.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.laker.admin.module.bus.entity.SubscriptionEntity;
import com.laker.admin.module.bus.service.SubscriptionService;
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
 * 业务表_订阅表
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:40:03
 */
@Api(tags = "业务表_订阅表")
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表,page(页数 默认为1),limit(一页显示的数量 默认为10)")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = subscriptionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据单个id获取信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SubscriptionEntity subscription = subscriptionService.getById(id);

        return R.ok().put("subscription", subscription);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody SubscriptionEntity subscription){
        UserAndDateUtil.setCreateUserInfoAndDate(subscription);
		subscriptionService.save(subscription);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody SubscriptionEntity subscription){
        subscription.setCreator(StpUtil.getLoginIdAsLong());
        UserAndDateUtil.setUpdateUserInfoAndDate(subscription);
		subscriptionService.updateById(subscription);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		subscriptionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
