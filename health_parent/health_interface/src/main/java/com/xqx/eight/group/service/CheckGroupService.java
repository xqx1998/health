package com.xqx.eight.group.service;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.pojo.CheckGroup;

/**
 * @author： xingquanxiang
 * createTime：2019/11/6 0:15
 * description:
 */
public interface CheckGroupService {
    /**
     * 根据条件分页查询检查组信息
     * @param queryPageBean
     * @return  PageResult分页结果封装对象
     */
    PageResult findPageByCondition(QueryPageBean queryPageBean);

    /**
     * 添加检查组信息
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 根据id查询检查组信息
     * @param id
     * @return  CheckGroup对象
     */
    CheckGroup findById(Integer id);

    /**
     * 检查组 编辑
     * @param checkGroup  检查组信息
     * @param checkitemIds  检查组关联检查项id们
     */
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 检查组 删除 （检查组与检查项关系）
     * @param id  检查组id
     */
    void delete(Integer id);
}
