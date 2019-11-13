package com.eight.group.mapper;

import com.eight.group.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author：xingquanxiang createTime：2019/11/12 15:34
 * description:
 */
public interface OrderMapper {
    /**
     * 新增预约
     * @param order
     */
    void add(Order order);

    /**
     * 动态条件查询
     * @param order
     * @return List<Order>
     */
    List<Order> findByCondition(Order order);

    /**
     * 根据预约id查询预约信息，包括体检人信息，套餐信息
     * @param id
     * @return  Map
     */
    Map findById4Detail(Integer id);

    /**
     * 根据日期统计预约数
     * @param date
     * @return Integer
     */
    Integer findOrderCountByDate(String date);

    /**
     * 根据日期统计预约数，统计指定日期之后的与约数
     * @param date
     * @return Integer
     */
    Integer findOrderCountAfterDate(String date);

    /**
     * 根据日期统计到诊数
     * @param date
     * @return Integer
     */
    Integer findVisitsCountByDate(String date);

    /**
     * 根据日期统计到诊数，统计指定日期之后的到诊数
     * @param date
     * @return Integer
     */
    Integer findVisitsCountAfterDate(String date);

    /**
     * 热门套餐，查询前5条
     * @return List<Map>
     */
    List<Map> findHotSetmeal();
}
