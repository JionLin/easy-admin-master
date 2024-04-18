package com.laker.admin.module.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.enums.CollStatusEnum;
import com.laker.admin.framework.model.PageResponse;
import com.laker.admin.framework.model.Response;
import com.laker.admin.framework.utils.EasyAdminSecurityUtils;
import com.laker.admin.module.bus.entity.BusSubmission;
import com.laker.admin.module.bus.entity.BusSubmissionImage;
import com.laker.admin.module.bus.entity.Coll;
import com.laker.admin.module.bus.pojo.*;
import com.laker.admin.module.bus.service.BusSubmissionImageService;
import com.laker.admin.module.bus.service.BusSubmissionService;
import com.laker.admin.module.bus.service.CollService;
import com.laker.admin.utils.UserAndDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 稿件表 前端控制器
 * </p>
 *
 * @author johnny
 * @since 2024-04-15
 */
@Api(tags ="稿件表" )
@RestController
@RequestMapping("/busSubmission")
public class BusSubmissionController {


    @Autowired
    private BusSubmissionService submissionService;

    @Autowired
    private BusSubmissionImageService submissionImageService;

    @Autowired
    private CollService collService;

    public static final Integer MIN = 4;
    public static final Integer MAX = 99;

    public static final String SEPARATOR = ",";

    /**
     * 稿件表保存
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "稿件表保存")
    public Response addColl(@RequestBody @Valid SubmissionSave submissionSave) {
        // 进行校验 只有已发布的
        Long collId = submissionSave.getCollId();
        Coll coll = collService.getOne(new QueryWrapper<Coll>().eq("id", collId).
                eq("status", CollStatusEnum.PUBLISHED_STATUS.getCode()));
        if (coll == null) {
            return Response.error("合集id 传递失败");
        }
        String url = submissionSave.getUrl();
        if (StringUtils.isEmpty(url) || url.split(SEPARATOR).length < MIN || url.split(SEPARATOR).length > MAX) {
            return Response.error("上传的漫画作品数量不符合要求");
        }
        BusSubmission submission = new BusSubmission();
        BeanUtils.copyProperties(submissionSave, submission);
        UserAndDateUtil.setCreateUserInfoAndDate(submission);

        boolean flag = submissionService.save(submission);
        if (!flag) {
            return Response.error("稿件保存错误");
        }
        Response<String> value = this.urlParamHandler(submission, url, false);
        if (value != null) {
            return value;
        }
        return Response.ok();
    }

    /**
     * deleteFlag 新增不需要删除   修改 需要删除
     */
    private Response<String> urlParamHandler(BusSubmission submission, String url, boolean deleteFlag) {
        if (!StringUtils.isEmpty(url)) {
            String[] urls = url.split(SEPARATOR);
            List<BusSubmissionImage> submissionImages = new ArrayList<>();
            List<String> images = Arrays.stream(urls).collect(Collectors.toList());
            for (int i = 0; i < images.size(); i++) {
                BusSubmissionImage entity = new BusSubmissionImage();
                entity.setPosition(i);
                entity.setUrl(images.get(i));
                entity.setSubmissionId(submission.getId());
                entity.setIsDeleted(CommonConstant.NOT_DELETED);
                UserAndDateUtil.setCreateUserInfoAndDate(entity);
                submissionImages.add(entity);
            }
            // 如果是修改,就需要把原先的进行删除
            if (deleteFlag) {
                List<BusSubmissionImage> imageList = submissionImageService.getSubmissionId(submission.getId().intValue());
                if (CollectionUtils.isNotEmpty(imageList)) {
                    List<BusSubmissionImage> list = imageList.stream().map(
                                    e -> {
                                        e.setIsDeleted(CommonConstant.DELETED);
                                        return e;
                                    })
                            .collect(Collectors.toList());
                    submissionImageService.saveOrUpdateBatch(list);
                }
            }
            submissionImageService.saveBatch(submissionImages);
        }
        return null;
    }


    @GetMapping("/{id}")
    @ApiOperation("查询单个稿件数据")
    public Response getSubmission(@PathVariable("id") Integer id) {
        BusSubmission busSubmission = submissionService.getById(id);
        if (busSubmission == null) {
            return Response.ok();
        }
        List<BusSubmissionImage> images = submissionImageService.getSubmissionId(id);
        BusSubmissionItem busSubmissionItem = new BusSubmissionItem();
        BeanUtils.copyProperties(busSubmission, busSubmissionItem);
        if (CollectionUtils.isNotEmpty(images)) {
            List<SubmissionImages> imagesList = new ArrayList<>();
            images.stream().forEach(e -> {
                SubmissionImages submissionImages = new SubmissionImages();
                BeanUtils.copyProperties(e, submissionImages);
                imagesList.add(submissionImages);
            });
            busSubmissionItem.setSubmissionImages(imagesList);
        }
        return Response.ok(busSubmissionItem);
    }


    @PostMapping("/update")
    @ApiOperation("修改单个稿件数据")
    public Response update(@RequestBody SubmissionUpdate submissionUpdate) {
        BusSubmission busSubmission = submissionService.getById(submissionUpdate.getId());
        if (busSubmission == null) {
            return Response.error("查询不到对应的稿件");
        }
        List<SubmissionImages> submissionImages = submissionUpdate.getSubmissionImages();
        if (CollectionUtils.isEmpty(submissionImages)) {
            return Response.error("上传稿件路径为空");
        }
        List<String> url = submissionImages.stream().map(SubmissionImages::getUrl).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(url) || url.size() < MIN || url.size() > MAX) {
            return Response.error("上传的漫画作品数量不符合要求");
        }
        BusSubmission submission = new BusSubmission();
        BeanUtils.copyProperties(submissionUpdate, submission);
        submissionService.updateById(submission);
        String urls = url.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SEPARATOR));
        Response<String> value = this.urlParamHandler(submission, urls, true);
        if (value != null) {
            return value;
        }
        return Response.ok();
    }

    @GetMapping("/list")
    @ApiOperation("稿件列表")
    public PageResponse list(@RequestParam(required = false, value = "status", defaultValue = "")
                                     String status,
                             @RequestParam(required = false, defaultValue = "1") long page,
                             @RequestParam(required = false, defaultValue = "10") long limit) {
        Page roadPage = new Page<>(page, limit);
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        QueryWrapper<BusSubmission> queryWrapper = new QueryWrapper<BusSubmission>().eq("creator", userId).
                eq("is_deleted", CommonConstant.NOT_DELETED);
        Page pageList;
        if (StringUtils.isEmpty(status)) {
            pageList = submissionService.page(roadPage, queryWrapper);
        } else {
            pageList = submissionService.page(roadPage, queryWrapper.eq("status", status));
        }
        return PageResponse.ok(pageList.getRecords(), pageList.getTotal());
    }


    @PostMapping("/calculation")
    @ApiOperation(value = "稿件计算")
    public Response collCalculation(@RequestBody SubmissionCalculation busSubmission) {
        BusSubmission byId = submissionService.getById(busSubmission.getId());
        if (byId == null) {
            return Response.error("稿件不存在");
        }
        BusSubmission param = SubmissionCalculation.getCalculationParam(byId, busSubmission);
        // 进行根据数据库里面的值,进行和前端给的值 相加
        boolean result = submissionService.updateById(param);
        if (result) {
            return Response.ok();
        } else {
            return Response.error("错误");
        }
    }


    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除单个稿件")
    public Response collCalculation(@PathVariable("id") Long id) {
        BusSubmission byId = submissionService.getById(id);
        if (byId==null){
            return Response.error("稿件不存在");
        }
        byId.setIsDeleted(CommonConstant.DELETED);
        boolean flag = submissionService.updateById(byId);
        if (flag){
            return Response.ok();
        }else {
            return Response.error("删除失败");
        }
    }





}
