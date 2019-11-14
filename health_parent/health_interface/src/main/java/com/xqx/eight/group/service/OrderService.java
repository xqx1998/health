package com.xqx.eight.group.service;

import com.eight.group.entity.Result;
import com.eight.group.pojo.Order;

import java.util.Map;

/**
 * @author： xingquanxiang
 * createTime：2019/11/12 14:51
 * description:
 */
public interface OrderService {
    /**
     * 在线预约
     * @param map 预约客户信息
     * @return Result
     */
    Result order(Map map) throws Exception;

    /**
     * 根据id查询预约信息
     * @param id
     * @return Order
     */
    Order findById(Integer id);
}
