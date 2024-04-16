package com.laker.admin.module.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laker.admin.constant.CommonConstant;
import com.laker.admin.framework.model.Response;
import com.laker.admin.framework.utils.EasyAdminSecurityUtils;
import com.laker.admin.module.bus.entity.BusSubmission;
import com.laker.admin.module.bus.entity.BusSubmissionComment;
import com.laker.admin.module.bus.pojo.SubmissionCommentSave;
import com.laker.admin.module.bus.pojo.SubmissionCommentVo;
import com.laker.admin.module.bus.service.BusSubmissionCommentService;
import com.laker.admin.module.bus.service.BusSubmissionService;
import com.laker.admin.utils.TreeUtil;
import com.laker.admin.utils.UserAndDateUtil;
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
 * 稿件评论表 前端控制器
 * </p>
 *
 * @author johnny
 * @since 2024-04-16
 */
@RestController
@RequestMapping("/busSubmissionComment")
public class BusSubmissionCommentController {


    @Autowired
    private BusSubmissionService submissionService;

    @Autowired
    private BusSubmissionCommentService submissionCommentService;

    @PostMapping("/save")
    public Response saveSubmissionCommentInfo(@RequestBody @Valid SubmissionCommentSave commentSave) {
        Long collId = commentSave.getSubmissionId();
        BusSubmission busSubmission = submissionService.getOne(new QueryWrapper<BusSubmission>().
                eq("id", collId).eq("is_deleted", CommonConstant.NOT_DELETED));
        if (busSubmission==null){
            return Response.error("稿件不存在");
        }
        // 如果有父id,就存在父id 下面
        BusSubmissionComment entity=new BusSubmissionComment();
        BeanUtils.copyProperties(commentSave,entity);
        UserAndDateUtil.setCreateUserInfoAndDate(entity);
        boolean flag = submissionCommentService.save(entity);
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
    @DeleteMapping("/{id}")
    public Response saveCollCommentInfo(@PathVariable("id") Long id) {
        // 当前用户的评论 才可以自己删除
        BusSubmissionComment byId = submissionCommentService.getById(id);
        Long userId = EasyAdminSecurityUtils.getCurrentUserInfo().getUserId();
        if (byId == null || !byId.getCreator().equals(userId)) {
            return Response.error("当前评论不存在或者不是本人新建评论");
        }
        byId.setIsDeleted(CommonConstant.DELETED);
        boolean flag = submissionCommentService.updateById(byId);
        if (flag) {
            return Response.ok();
        } else {
            return Response.error("删除失败");
        }
    }


    /**
     * 评论列表-根据集合id 或者当前评论id
     *
     * @return
     */
    @ApiOperation("parCommentId 如果是评论下的第一级,那么传递0,如果不是,传递上一级的父id")
    @GetMapping("/list/{submissionId}/{parCommentId}")
    public Response list(@PathVariable("submissionId")Long submissionId,
                         @PathVariable(value = "parCommentId") Long parCommentId) {
        // 根据集合id 查询所有,然后进行递归查询
        List<BusSubmission> busSubmissions = submissionService.list(new QueryWrapper<BusSubmission>().eq("id", submissionId).
                eq("is_deleted", CommonConstant.NOT_DELETED));
        List<SubmissionCommentVo>  submissionCommentVos=new ArrayList<>();
        // 进行构造数据,构造多级参数
        if (CollectionUtils.isNotEmpty(busSubmissions)){
            busSubmissions.stream().forEach(e->{
                SubmissionCommentVo commentVo = new SubmissionCommentVo();
                BeanUtils.copyProperties(e,commentVo);
                submissionCommentVos.add(commentVo);
            });
        }
        List<SubmissionCommentVo> vos = TreeUtil.toTreeSubmissionComment(submissionCommentVos, parCommentId);
        return Response.ok(vos);
    }

}
