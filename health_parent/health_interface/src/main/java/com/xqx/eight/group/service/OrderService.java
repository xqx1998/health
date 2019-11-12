package com.xqx.eight.group.service;

import com.eight.group.entity.Result;

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
}
