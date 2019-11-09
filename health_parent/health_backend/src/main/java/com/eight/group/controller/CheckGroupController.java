package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.entity.Result;
import com.eight.group.pojo.CheckGroup;
import com.xqx.eight.group.service.CheckGroupService;
import com.xqx.eight.group.service.CheckItemService;
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
    /**
     * 检查组 按照条件进行分页查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            return checkGroupService.findPageByCondition(queryPageBean);
        } catch (Exception e) {
            return new PageResult(0L, null);
        }
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Result findAll() {
        try {
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupService.findAll());
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            System.out.println("checkGroup = " + checkGroup);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,
                    checkGroup);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }


}
