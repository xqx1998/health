package com.xqx.eight.group.service;

import com.eight.group.pojo.OrderSetting;

import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/9 16:16
 * description:
 */
public interface OrderSettingService {
    /**
     * 批量添加预约设置数据
     * @param orderSettings
     */
    void add(List<OrderSetting> orderSettings);

    /**
     * 获取某月的所有预约设置数据
     * @param date
     * @return List<OrderSetting>
     */
    List<OrderSetting> getOrderSettingByMonth(String date);

    /**
     * 根据日期修改 可预约人数
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);

}
