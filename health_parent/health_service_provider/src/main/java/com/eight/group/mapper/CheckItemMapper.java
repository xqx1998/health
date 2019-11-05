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
     * @return List<CheckItem>
     */
    List<CheckItem> findPage();

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
}
