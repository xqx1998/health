package com.xqx.eight.group.service;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.pojo.CheckItem;


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

    /**
     * 更新检查项信息
     * @param checkItem  要编辑的CheckItem对象
     */
    void edit(CheckItem checkItem);

    /**
     * 删除检查项
     * @param id 检查项id
     */
    void delete(int id);

    /**
     * 检查项 查询所有
     * @return PageResult对象
     */
    PageResult findAll();
}
