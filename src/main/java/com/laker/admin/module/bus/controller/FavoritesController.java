package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.entity.FavoritesEntity;
import com.laker.admin.module.bus.service.FavoritesService;
import com.laker.admin.utils.PageUtils;
import com.laker.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务_我的收藏
 *  需要改动的地方
 *  1。 class 加 @Api(tags = "业务_我的收藏") ok
 *  2. 改动请求 postmapper ok
 *  3.方法上面加上  @ApiOperation(value = "") ok
 *  4. 删除导入类 3个 ok
 * @author johnny
 * @email johnny@gmail.com
 * @date 2024-04-20 12:23:28
 */
@Api(tags = "业务_我的收藏")
@RestController
@RequestMapping("bus/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = favoritesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        FavoritesEntity favorites = favoritesService.getById(id);

        return R.ok().put("favorites", favorites);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody FavoritesEntity favorites) {
        favoritesService.save(favorites);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public R update(@RequestBody FavoritesEntity favorites) {
        favoritesService.updateById(favorites);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        favoritesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
