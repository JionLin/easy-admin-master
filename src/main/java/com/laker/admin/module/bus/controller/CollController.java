package com.laker.admin.module.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laker.admin.enums.CollTypeEnums;
import com.laker.admin.framework.model.Response;
import com.laker.admin.module.bus.entity.Coll;
import com.laker.admin.module.bus.pojo.CollSave;
import com.laker.admin.module.bus.service.CollService;
import com.laker.admin.utils.UserAndDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
    /**
     * 新建合集
     *
     * @return
     */
    @PostMapping("/save")
    public Response addColl(@RequestBody @Valid CollSave collSave) {
        // 1. 名称不能为空,且不能重复
        String name = collSave.getName();
        List<Coll> collList = collService.list(new QueryWrapper<Coll>().eq("name", name));
        if (collList.size() != 0){
            return Response.error("名称已存在");
        }
        // 2. 类型进行参数判断
        String type = collSave.getType();
        if (!CollTypeEnums.isExist(type)){
            return Response.error("类型不存在");
        }
        Coll coll=new Coll();
        BeanUtils.copyProperties(collSave,coll);
        UserAndDateUtil.setCreateUserInfoAndDate(coll);
        // 3. 封面上传给到服务器
        boolean flag = collService.save(coll);
        if (flag){
            return Response.ok();
        }else {
            return Response.error("save 错误");
        }



    }


}
