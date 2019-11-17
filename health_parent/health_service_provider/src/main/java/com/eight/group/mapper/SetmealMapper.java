package com.eight.group.mapper;

import com.eight.group.pojo.CheckGroup;
import com.eight.group.pojo.Setmeal;
import com.eight.group.vo.SetmealReportVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/8 15:17
 * description:
 */
public interface SetmealMapper {
    /**
     * 新增套餐
     * @param setmeal 套餐信息
     */
    void add(Setmeal setmeal);

    /**
     * 设置套餐与检查关联id
     * @param map
     */
    void setSetmealAndCheckGroup(HashMap<String, Object> map);

    /**
     * 套餐分页查询
     * @return List<Setmeal>
     * @param queryString
     */
    List<Setmeal> findPage(String queryString);

    /**
     * 根据套餐id 查询套餐信息及关联检查组id们
     * @param id 套餐id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 根据套餐id删除 与检查组关联表信息
     * @param id 套餐id
     */
    void deleteSetmealAndCheckGroup(Integer id);

    /**
     * 套餐信息 更新
     * @param setmeal
     */
    void edit(Setmeal setmeal);

    /**
     * 根据套餐id 删除套餐信息
     * @param id 套餐id
     */
    void delete(Integer id);

    List<Setmeal> findAll();

    Setmeal findByIdDetail(int intValue);

}
