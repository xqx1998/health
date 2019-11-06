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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    /**
     * 添加检查组信息
     *
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupMapper.add(checkGroup);
        if (checkGroup.getId()!=null && checkitemIds.length>0) {
            this.setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
        }
    }

    /**
     * 根据id查询检查组信息
     *
     * @param id
     * @return CheckGroup对象
     */
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }


    /**
     * 检查组 编辑
     *
     * @param checkGroup   检查组信息
     * @param checkitemIds 检查组关联检查项id们
     */
    @Override
    @Transactional
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupMapper.deleteCheckGroupAndItemByGroupId(checkGroup.getId());
        checkGroupMapper.update(checkGroup);
        if (checkGroup.getId()!=null && checkitemIds.length>0) {
            this.setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
        }
    }

    /**
     * 设置 检查组与检查项 关联
     * @param checkgroupId
     * @param checkitemIds
     */
    private void setCheckGroupAndCheckItem(int checkgroupId, Integer[] checkitemIds){
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("checkgroupId", checkgroupId);
        map.put("checkitemIds", checkitemIds);
        checkGroupMapper.setCheckGroupAndCheckItem(map);
    }

    /**
     * 检查组 删除 （检查组与检查项关系）
     *
     * @param id 检查组id
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        checkGroupMapper.deleteCheckGroupAndItemByGroupId(id);
        checkGroupMapper.delete(id);
    }
}
