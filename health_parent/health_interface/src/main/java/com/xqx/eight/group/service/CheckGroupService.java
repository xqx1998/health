package com.xqx.eight.group.service;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;

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
}
