package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.constant.RedisConstant;
import com.eight.group.mapper.OrderSettingMapper;
import com.eight.group.pojo.OrderSetting;
import com.xqx.eight.group.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * @author：xingquanxiang createTime：2019/11/9 16:41
 * description:
 */
@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private static OrderSettingMapper orderSettingMapper;

    @Autowired
    private static JedisPool jedisPool;

    //todo  如何进行加载
    /*static {
        List<Date> allOrderDate = orderSettingMapper.getAllOrderDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Jedis resource = jedisPool.getResource();
        long begin = System.currentTimeMillis();
        for (Date date : allOrderDate) {
            resource.sadd(RedisConstant.ORDER_SETTING_DB_ORDER_DATE, simpleDateFormat.format(date));
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时："+(end-begin));
    }*/



    /**
     * 批量添加预约设置数据
     *
     * @param orderSettingList
     */
    @Override
    public void add(List<OrderSetting> orderSettingList) {
        if (orderSettingList != null && orderSettingList.size() > 0) {
            Set<String> orderDates = jedisPool.getResource().smembers(RedisConstant.ORDER_SETTING_DB_ORDER_DATE);
            for (OrderSetting orderSetting : orderSettingList) {
                // 检查此数据（日期）是否存在
                if (orderDates.contains(orderSetting.getOrderDate().toString())) {
                    //已存在，执行更新操作
                    orderSettingMapper.editNumberByOrderDate(orderSetting);
                } else {
                    // 不存在，执行添加操作 数据库中
                    orderSettingMapper.add(orderSetting);
                    //添加预约设置的日期到redis缓存中
                    jedisPool.getResource().sadd(RedisConstant.ORDER_SETTING_DB_ORDER_DATE,
                            orderSetting.getOrderDate().toString());
                }
            }
        }
    }

    /**
     * 获取某月的所有预约设置数据
     *
     * @param date
     * @return List<OrderSetting>
     */
    @Override
    public List<OrderSetting> getOrderSettingByMonth(String date) {
        String dateBegin = date + "-1";//2019-3-1
        String dateEnd = date + "-31";//2019-3-31
        Map map = new HashMap();
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        return orderSettingMapper.getOrderSettingByMonth(map);
    }

    /**
     * 根据日期修改 可预约人数
     *
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        Set<String> orderDates = jedisPool.getResource().smembers(RedisConstant.ORDER_SETTING_DB_ORDER_DATE);
        // 检查此数据（日期）是否存在
        if (orderDates.contains(orderSetting.getOrderDate().toString())) {
            //已存在，执行更新操作
            orderSettingMapper.editNumberByOrderDate(orderSetting);
        } else {
            // 不存在，执行添加操作 数据库中
            orderSettingMapper.add(orderSetting);
            //添加预约设置的日期到redis缓存中
            jedisPool.getResource().sadd(RedisConstant.ORDER_SETTING_DB_ORDER_DATE,
                    orderSetting.getOrderDate().toString());
        }
    }
}
