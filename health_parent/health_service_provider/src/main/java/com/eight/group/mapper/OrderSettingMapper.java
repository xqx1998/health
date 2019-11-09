package com.eight.group.mapper;

import com.eight.group.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author： xingquanxiang
 * createTime：2019/11/9 16:46
 * description:
 */
public interface OrderSettingMapper {

    /**
     * 根据日期查询预约是否已存在
     * @param orderDate 预约日期
     * @return
     */
    long findCountByOrderDate(Date orderDate);

    /**
     * 根据日期更新预约数据
     * @param orderSetting  预约数据对象
     */
    void editNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 新增预约数据
     * @param orderSetting 预约数据对象
     */
    void add(OrderSetting orderSetting);

    /**
     * 获取某月的预约设置数据
     * @param map
     * @return List<OrderSetting>
     */
    List<OrderSetting> getOrderSettingByMonth(Map map);

    /**
     *
     */
    List<Date> getAllOrderDate();
}
