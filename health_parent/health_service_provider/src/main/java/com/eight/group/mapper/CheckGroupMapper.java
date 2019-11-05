package com.eight.group.mapper;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.pojo.CheckGroup;

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
}
