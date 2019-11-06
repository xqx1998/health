package com.eight.group.mapper;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.pojo.CheckGroup;

import java.util.HashMap;
import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/6 0:24
 * description:
 */
public interface CheckGroupMapper {
    /**
     * 根据条件分页查询检查组信息
     * @param queryString
     * @return List<CheckGroup> 对象
     */
    List<CheckGroup> findPageByCondition(String queryString);

    /**
     * 检查组添加
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 设置 检查组与检查项 id关联
     * @param map
     */
    void setCheckGroupAndCheckItem(HashMap<String, Object> map);

    /**
     * 根据检查组id查询 检查组信息 包含所属检查项列表
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 删除 根据检查组id删除检查组与检查项关系
     * @param id
     */
    void deleteCheckGroupAndItemByGroupId(Integer id);

    /**
     * 更新 检查组信息
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);

    /**
     * 检查组 删除
     * @param id  检查组id
     */
    void delete(Integer id);
}
