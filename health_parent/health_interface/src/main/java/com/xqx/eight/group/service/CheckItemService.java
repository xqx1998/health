package com.xqx.eight.group.service;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.pojo.CheckItem;

import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/5 11:52
 * description:  检查项 接口
 */
public interface CheckItemService {
    /**
     * 添加检查项
     * @param checkItem  检查项对象
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return PageResult对象
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询 检查项信息
     * @param id
     * @return CheckItem对象
     */
    CheckItem findById(int id);
}
