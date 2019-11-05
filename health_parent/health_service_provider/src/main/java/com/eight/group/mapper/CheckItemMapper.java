package com.eight.group.mapper;

import com.eight.group.pojo.CheckItem;

import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/5 14:05
 * description:
 */
public interface CheckItemMapper {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString 查询条件
     * @return List<CheckItem>
     */
    List<CheckItem> findPageByCondition(String queryString);

    /**
     * 根据id查询 检查项信息
     * @param id
     * @return CheckItem对象
     */
    CheckItem findById(int id);

    /**
     * 更新检查项信息
     * @param checkItem  要编辑的CheckItem对象
     * @return
     */
    void edit(CheckItem checkItem);

    /**
     * 查询 是否有与检查项关联的检查组 计数
     * @param id
     * @return
     */
    long findCountByCheckItemId(Integer id);

    /**
     * 删除检查项
     * @param id 检查项id
     */
    void delete(int id);
}
