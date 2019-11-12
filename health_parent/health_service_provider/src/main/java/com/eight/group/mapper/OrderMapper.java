package com.eight.group.mapper;

import com.eight.group.pojo.Order;

import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/12 15:34
 * description:
 */
public interface OrderMapper {
    int add(Order order);

    List<Order> findByCondition(Order order);
}
