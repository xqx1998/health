package com.eight.group.controller;
import	java.awt.Desktop.Action;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.eight.group.constant.MessageConstant;
import com.eight.group.constant.RedisConstant;
import com.eight.group.entity.Result;
import com.eight.group.pojo.Order;
import com.eight.group.utils.SMSUtils;
import com.xqx.eight.group.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author：xingquanxiang createTime：2019/11/12 14:41
 * description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService OrderService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 在线体检预约
     * @param map 预约提交数据表单
     * @return Result
     */
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        String telephone = (String)map.get("telephone");
        //从redis中获取保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisConstant.SENDTYPE_ORDER);
        // 获取表单中的验证码
        String validateCode = (String)map.get("validateCode");
        //验证码比对
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //比对成功
            //设置预约类型，分为微信预约、电话预约
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            Result result = null;
            try {
                //通过Dubbo远程调用服务实现在线预约业务处理
                result = OrderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ORDER_FAIL);
            }
            if (result.isFlag()){
                //预约成功，为用户发送短信
                try {
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, (String)map.get("orderDate"));
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }else{
            //验证码输入错误
            return new Result(true, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, OrderService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
