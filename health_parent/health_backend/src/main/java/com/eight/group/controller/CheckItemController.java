package com.eight.group.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.entity.Result;
import com.eight.group.pojo.CheckItem;
import com.xqx.eight.group.service.CheckItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：xingquanxiang createTime：2019/11/5 11:45
 * description:
 */
@Controller
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    /**
     * 检查项添加
     * @param checkItem
     * @return Result对象结果
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        System.out.println("queryPageBean = " + queryPageBean);
        try {
            return checkItemService.findPage(queryPageBean);
        } catch (Exception e) {
            return new PageResult(0L, null);
        }
    }

    /**
     * 检查项根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(int id){
        try {
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemService.findById(id));
        } catch (Exception e) {
            return  new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * 检查项编辑
     * @param checkItem
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 检查项根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int id){
        try {
            checkItemService.delete(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }


}
