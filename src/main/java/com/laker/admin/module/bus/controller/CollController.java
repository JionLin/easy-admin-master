package com.laker.admin.module.bus.controller;

import com.laker.admin.module.bus.pojo.collSave;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author johnny
 * @Classname CollController
 * @Description 合集 controller
 * @Date 2024/4/13 11:48
 */
@RestController
@RequestMapping("/coll")
public class CollController {

    /**
     * 新建合集
     *
     * @return
     */
    @PostMapping("/save")
    public String addColl(@RequestBody collSave collSave) {

        return null;
    }


}
