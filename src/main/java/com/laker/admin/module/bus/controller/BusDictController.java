package com.laker.admin.module.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.framework.model.PageResponse;
import com.laker.admin.framework.model.Response;
import com.laker.admin.framework.utils.EasyAdminSecurityUtils;
import com.laker.admin.module.bus.entity.BusDict;
import com.laker.admin.module.bus.pojo.BusDictSave;
import com.laker.admin.module.bus.service.BusDictService;
import com.laker.admin.utils.UserAndDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 业务字典表 前端控制器
 * </p>
 *
 * @author johnny
 * @since 2024-04-16
 */
@Api(tags ="业务字典表")
@RestController
@RequestMapping("/busDict")
public class BusDictController {

    @Autowired
    private BusDictService dictService;

    @ApiOperation("保存字典信息")
    @PostMapping("/save")
    public Response saveDict(@RequestBody BusDictSave busDictSave){
        BusDict entity=new BusDict();
        BeanUtils.copyProperties(busDictSave,entity);
        UserAndDateUtil.setCreateUserInfoAndDate(entity);
        boolean flag = dictService.save(entity);
        if (flag){
            return Response.ok();
        }else {
            return Response.error("保存失败");
        }

    }

    /**
     * 信息
     */
    @ApiOperation(value = "获取对应的字典数据")
    @GetMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        BusDict dict = dictService.getById(id);

        return Response.ok(dict);
    }


    @ApiOperation(value = "删除对应的字典数据")
    @DeleteMapping("/{id}")
    public Response deleteById(@PathVariable("id") Long id){
        BusDict byId = dictService.getById(id);
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        if (byId==null ||!byId.getCreator().equals(userId)){
            return Response.error("字典删除错误");
        }
        byId.setIsDeleted(CommonConstant.DELETED);
        boolean flag = dictService.updateById(byId);
        if (flag){
            return Response.ok();
        }else {
            return Response.error("删除失败");
        }
    }

    @ApiOperation(value = "字典列表")
    @GetMapping("/list")
    public PageResponse list(@RequestParam(required = false, defaultValue = "1") long page,
                             @RequestParam(required = false, defaultValue = "10") long limit){
        Page roadPage = new Page<>(page, limit);
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        QueryWrapper<BusDict> queryWrapper = new QueryWrapper<BusDict>().eq("creator", userId).
                eq("is_deleted", CommonConstant.NOT_DELETED);
        Page pageList = dictService.page(roadPage, queryWrapper);
        return PageResponse.ok(pageList.getRecords(), pageList.getTotal());
    }


}
