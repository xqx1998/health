package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.mapper.CheckGroupMapper;
import com.eight.group.pojo.CheckGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xqx.eight.group.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/6 0:22
 * description:
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;
    /**
     * 根据条件分页查询检查组信息
     *
     * @param queryPageBean
     * @return PageResult分页结果封装对象
     */
    @Override
    public PageResult findPageByCondition(QueryPageBean queryPageBean) {
        //调用分页助手api 设置当前页 ， 每页大小
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //执行查询，获取查询结果列表
        List<CheckGroup> checkGroupList = checkGroupMapper.findPageByCondition(queryPageBean.getQueryString());
        //返回 分页实体类封装对象
        return new PageResult(new PageInfo<>(checkGroupList).getTotal(), checkGroupList);
    }
}
