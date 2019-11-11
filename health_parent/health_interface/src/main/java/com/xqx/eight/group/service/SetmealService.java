package com.xqx.eight.group.service;

import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.pojo.Setmeal;

import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/8 11:15
 * description:
 */
public interface SetmealService {
    /**
     * 新增套餐
     * @param setmeal  套餐信息
     * @param checkgroupIds 所属检查组id们
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 套餐分页查询
     * @param queryPageBean
     * @return PageResult
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询套餐数据
     * @param id
     * @return Setmeal
     */
    Setmeal findById(Integer id);

    /**
     * 编辑套餐
     * @param setmeal 套餐对象
     * @param checkgroupIds 关联检查组id们
     */
    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 根据id删除套餐
     * @param id 套餐id
     */
    void delete(Integer id);

    /**
     * 查询所有套餐
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 根据套餐id查询套餐详情（套餐基本详情， 套餐对应的检查组信息， 检查组对应的检查项信息）
     * @param id
     * @return　Setmeal
     */
    Setmeal findByIdDetail(Integer id);
}
