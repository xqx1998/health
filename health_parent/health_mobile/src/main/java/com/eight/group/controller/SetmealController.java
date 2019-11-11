package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.Result;
import com.xqx.eight.group.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：xingquanxiang createTime：2019/11/10 1:31
 * description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    /**
     * 获取套餐
     * @return
     */
    @RequestMapping("/getSetmeal")
    public Result getSetmeal() {
        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS);
    }
}
