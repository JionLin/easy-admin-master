package com.laker.admin.module.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.enums.CollStatusEnum;
import com.laker.admin.enums.CollTypeEnums;
import com.laker.admin.framework.model.PageResponse;
import com.laker.admin.framework.model.Response;
import com.laker.admin.framework.utils.EasyAdminSecurityUtils;
import com.laker.admin.module.bus.entity.BusSubmission;
import com.laker.admin.module.bus.entity.Coll;
import com.laker.admin.module.bus.pojo.CollCalculation;
import com.laker.admin.module.bus.pojo.CollSave;
import com.laker.admin.module.bus.pojo.CollUpdate;
import com.laker.admin.module.bus.service.BusSubmissionService;
import com.laker.admin.module.bus.service.CollService;
import com.laker.admin.utils.UserAndDateUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author johnny
 * @Classname CollController
 * @Description 合集 controller
 * @Date 2024/4/13 11:48
 */
@RestController
@RequestMapping("/coll")
public class CollController {

    @Autowired
    private CollService collService;

    @Autowired
    private BusSubmissionService submissionService;

    /**
     * 新建合集  EasyAdminSecurityUtils
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "合集保存")
    public Response addColl(@RequestBody @Valid CollSave collSave) {
        // 1. 名称不能为空,且不能重复
        String name = collSave.getName();
        List<Coll> collList = collService.list(new QueryWrapper<Coll>().eq("name", name));
        if (collList.size() != 0) {
            return Response.error("名称已存在");
        }
        // 2. 类型进行参数判断
        String type = collSave.getType();
        if (!CollTypeEnums.isExist(type)) {
            return Response.error("类型不存在");
        }
        Coll coll = new Coll();
        BeanUtils.copyProperties(collSave, coll);
        UserAndDateUtil.setCreateUserInfoAndDate(coll);
        // 3. 封面上传给到服务器
        boolean flag = collService.save(coll);
        if (flag) {
            return Response.ok();
        } else {
            return Response.error("save 错误");
        }

    }

    @PostMapping("/update")
    @ApiOperation(value = "合集更新")
    public Response updateColl(@RequestBody CollUpdate collUpdate) {
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        // 名称重复问题
        Long id = collUpdate.getId();
        String name = collUpdate.getName();
        Coll byId = collService.getById(id);
        if (byId == null || !byId.getCreator().equals(userId)) {
            return Response.error("不允许修改");
        }
        if (Objects.equals(byId.getStatus(), CollStatusEnum.PUBLISHED_STATUS.getCode()) ||
                Objects.equals(byId.getStatus(), CollStatusEnum.UNDER_REVIEW_STATUS.getCode())) {
            return Response.error("状态是已发布或者审核中不允许修改");
        }
        List<Coll> collList = collService.list(new QueryWrapper<Coll>().eq("id", id).
                eq("name", name));
        // 代表没有改变名字
        if (collList.size() == 1) {
            Coll coll = new Coll();
            BeanUtils.copyProperties(collUpdate, coll);
            UserAndDateUtil.setUpdateUserInfoAndDate(coll);
            boolean flag = collService.updateById(coll);
            if (flag) {
                return Response.ok();
            }
        } else {
            // 改变了名字后,就需要去根据名字去数据库查询
            List<Coll> list = collService.list(new QueryWrapper<Coll>().eq("name", name).ne("id", id));
            if (CollectionUtils.isNotEmpty(list)) {
                return Response.error("名称重复,不允许修改");
            } else {
                Coll coll = new Coll();
                BeanUtils.copyProperties(collUpdate, coll);
                UserAndDateUtil.setUpdateUserInfoAndDate(coll);
                boolean flag = collService.updateById(coll);
                if (flag) {
                    return Response.ok();
                }
            }
        }
        return Response.error("修改失败");
    }


    @GetMapping("/list")
    @ApiOperation(value = "合集列表")
    public PageResponse listColl(@RequestParam(required = false, value = "status", defaultValue = "") String status,
                                 @RequestParam(required = false, defaultValue = "1") long page,
                                 @RequestParam(required = false, defaultValue = "10") long limit) {
        Page roadPage = new Page<>(page, limit);
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        QueryWrapper<Coll> queryWrapper = new QueryWrapper<Coll>().eq("creator", userId).
                eq("is_deleted", CommonConstant.NOT_DELETED);
        Page pageList;
        if (StringUtils.isEmpty(status)) {
            pageList = collService.page(roadPage, queryWrapper);
        } else {
            pageList = collService.page(roadPage, queryWrapper.eq("status", status));
        }
        return PageResponse.ok(pageList.getRecords(), pageList.getTotal());
    }

    // 收藏数 正常加1   阅读数 正常+1 月票数 +1  评论数 后台计算 吐槽数 +1  分享数 +1
    @PostMapping("/calculation")
    @ApiOperation(value = "合集计算")
    public Response collCalculation(@RequestBody CollCalculation collCalculation) {
        Coll byId = collService.getById(collCalculation.getId());
        if (byId == null) {
            return Response.error("合集不存在");
        }
        Coll param = CollCalculation.getCalculationParam(byId, collCalculation);
        // 进行根据数据库里面的值,进行和前端给的值 相加
        boolean result = collService.updateById(param);
        if (result) {
            return Response.ok();
        } else {
            return Response.error("错误");
        }
    }


    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除单个合集")
    public Response deleteById(@PathVariable("id") Long id) {
        Coll byId = collService.getById(id);
        if (byId==null){
            return Response.error("合集不存在");
        }
        // 查看合集下是否存在稿件
        Long collId = byId.getId();
        List<BusSubmission> submissionList=submissionService.selectListByCollId(collId);
        if (CollectionUtils.isNotEmpty(submissionList)){
            Collection<String> titles = submissionList.stream().map(e -> e.getTitle()).collect(Collectors.toList());
            return Response.error("合集下存在对应的稿件,不能删除,标题有: "+titles);
        }else {
            byId.setIsDeleted(CommonConstant.DELETED);
            boolean flag = collService.updateById(byId);
            if (flag){
                return Response.ok();
            }else {
                return Response.error("删除失败");
            }
        }
    }

    @ApiOperation("获取单个合集信息")
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        Coll coll = collService.getById(id);
        return Response.ok(coll);
    }
}
