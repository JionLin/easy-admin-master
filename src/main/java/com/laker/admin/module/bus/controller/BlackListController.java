package com.laker.admin.module.bus.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.laker.admin.module.bus.entity.BlackListEntity;
import com.laker.admin.module.bus.service.BlackListService;
import com.laker.admin.module.sys.entity.SysUser;
import com.laker.admin.module.sys.service.ISysUserService;
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
 * 业务表_黑名单
 *
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 16:40:02
 */
@Api(tags = "业务表_黑名单")
@RestController
@RequestMapping("/blacklist")
public class BlackListController {
    @Autowired
    private BlackListService blackListService;

    @Autowired
    ISysUserService sysUserService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表,page(页数 默认为1-非必传),limit(一页显示的数量 默认为10-非必传)")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = blackListService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据单个id获取信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		BlackListEntity blackList = blackListService.getById(id);

        return R.ok().put("blackList", blackList);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody BlackListEntity blackList){
        // 进行校验 黑名单id
        Long blockUserId = blackList.getBlockUserId();
        SysUser user = sysUserService.getById(blockUserId);
        if (user==null){
            return R.error("黑名单用户不存在");
        }
        blackList.setBlockUserName(user.getNickName());
        UserAndDateUtil.setCreateUserInfoAndDate(blackList);
		blackListService.save(blackList);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody BlackListEntity blackList){
        blackList.setCreator(StpUtil.getLoginIdAsLong());
        UserAndDateUtil.setUpdateUserInfoAndDate(blackList);
		blackListService.updateById(blackList);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		blackListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
