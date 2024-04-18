package com.laker.admin.module.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.framework.model.Response;
import com.laker.admin.framework.utils.EasyAdminSecurityUtils;
import com.laker.admin.module.bus.entity.BusCollComment;
import com.laker.admin.module.bus.entity.Coll;
import com.laker.admin.module.bus.pojo.CollCommentSave;
import com.laker.admin.module.bus.pojo.CollCommentVo;
import com.laker.admin.module.bus.service.BusCollCommentService;
import com.laker.admin.module.bus.service.CollService;
import com.laker.admin.utils.TreeUtil;
import com.laker.admin.utils.UserAndDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 合集评论表 前端控制器
 * </p>
 *
 * @author johnny
 * @since 2024-04-16
 */
@Api(tags ="合集评论")
@RestController
@RequestMapping("/busCollComment")
public class BusCollCommentController {

    @Autowired
    private BusCollCommentService collCommentService;

    @Autowired
    private CollService collService;

    @ApiOperation(value ="保存评论")
    @PostMapping("/save")
    public Response saveCollCommentInfo(@RequestBody @Valid CollCommentSave commentSave) {
        Long collId = commentSave.getCollId();
        Coll coll = collService.getOne(new QueryWrapper<Coll>().eq("id", collId).eq("is_deleted", CommonConstant.NOT_DELETED));
        if (coll==null){
            return Response.error("合集不存在");
        }
        // 如果有父id,就存在父id 下面
        BusCollComment entity = new BusCollComment();
        BeanUtils.copyProperties(commentSave, entity);
        UserAndDateUtil.setCreateUserInfoAndDate(entity);
        boolean flag = collCommentService.save(entity);
        if (flag) {
            return Response.ok();
        } else {
            return Response.error("保存失败");
        }
    }


    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除评论,当前用户的评论 才可以自己删除")
    @DeleteMapping("/{id}")
    public Response saveCollCommentInfo(@PathVariable("id") Long id) {
        // 当前用户的评论 才可以自己删除
        BusCollComment byId = collCommentService.getById(id);
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        if (byId == null || !byId.getCreator().equals(userId)) {
            return Response.error("当前评论不存在或者不是本人新建评论");
        }
        byId.setIsDeleted(CommonConstant.DELETED);
        boolean flag = collCommentService.updateById(byId);
        if (flag) {
            return Response.ok();
        } else {
            return Response.error("保存失败");
        }
    }


    /**
     * 评论列表-根据集合id 或者当前评论id
     *
     * @return
     */
    @ApiOperation("parCommentId 如果是评论下的第一级,那么传递0,如果不是,传递上一级的父id")
    @GetMapping("/list/{collId}/{parCommentId}")
    public Response list(@PathVariable("collId")Long collId,
                             @PathVariable(value = "parCommentId") Long parCommentId) {
        // 根据集合id 查询所有,然后进行递归查询
        List<BusCollComment> busCollComments = collCommentService.list(new QueryWrapper<BusCollComment>().eq("coll_id", collId).
                eq("is_deleted", CommonConstant.NOT_DELETED));
        List<CollCommentVo>  commentVos=new ArrayList<>();
        // 进行构造数据,构造多级参数
        if (CollectionUtils.isNotEmpty(busCollComments)){
            busCollComments.stream().forEach(e->{
                CollCommentVo commentVo=new CollCommentVo();
                BeanUtils.copyProperties(e,commentVo);
                commentVos.add(commentVo);
            });
        }
        List<CollCommentVo> vos = TreeUtil.toTreeCollComment(commentVos, parCommentId);
        return Response.ok(vos);
    }


}
