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
     * 获取套餐列表
     * @return Result
     */
    @RequestMapping("/getSetmeal")
    public Result getSetmeal() {
        try {
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, setmealService.findAll());
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    /**
     * 根据id查询套餐信息
     * @param id 套餐id
     * @return Result
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        if (id!=null){
            try {
                return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealService.findByIdDetail(id));
            } catch (Exception e) {
                return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
            }
        }else {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
