package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.mapper.SetmealMapper;
import com.eight.group.pojo.CheckGroup;
import com.eight.group.pojo.Setmeal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xqx.eight.group.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/8 15:13
 * description:
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 新增套餐
     *
     * @param setmeal       套餐信息
     * @param checkgroupIds 所属检查组id们
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.add(setmeal);
        this.setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
    }

    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds){
        if (checkgroupIds!=null && checkgroupIds.length>0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("setmealId", id);
            map.put("checkgroupIds", checkgroupIds);
            setmealMapper.setSetmealAndCheckGroup(map);
        }
    }
    /**
     * 套餐分页查询
     *
     * @param queryPageBean  分页参数
     * @return PageResult
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        List<Setmeal> list = setmealMapper.findPage(queryPageBean.getQueryString());
        return new PageResult(new PageInfo<>(list).getTotal(), list);
    }

    /**
     * 根据id查询套餐数据
     *
     * @param id  套餐id
     * @return Setmeal 套餐对象
     */
    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id.intValue());
    }

    /**
     * 编辑套餐
     *
     * @param setmeal       套餐对象
     * @param checkgroupIds 关联检查组id们
     */
    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.deleteSetmealAndCheckGroup(setmeal.getId());
        setmealMapper.edit(setmeal);
        this.setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
    }

    /**
     * 根据id删除套餐
     *
     * @param id 套餐id
     */
    @Override
    public void delete(Integer id) {
        setmealMapper.deleteSetmealAndCheckGroup(id);
        setmealMapper.delete(id);
    }
}
