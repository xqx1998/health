package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.xqx.eight.group.service.CheckGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：xingquanxiang createTime：2019/11/6 0:14
 * description:
 */
@Controller
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        System.out.println("queryPageBean = " + queryPageBean);
        try {
            return checkGroupService.findPageByCondition(queryPageBean);
        } catch (Exception e) {
            return new PageResult(0L, null);
        }
    }

}
