package com.eight.group.controller;

import com.aliyuncs.exceptions.ClientException;
import com.eight.group.constant.MessageConstant;
import com.eight.group.constant.RedisConstant;
import com.eight.group.entity.Result;
import com.eight.group.utils.SMSUtils;
import com.eight.group.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author：xingquanxiang createTime：2019/11/12 8:31
 * description:
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //生成四位验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
       /* try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode.toString());
        } catch (ClientException e) {
            //todo 异常处理
            e.printStackTrace();
            //验证码发送失败
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        System.out.println("发送的手机验证码为："+validateCode+", 接收手机号："+telephone);
        //将生成的验证码存储到redis中 5分钟后失效
        jedisPool.getResource().setex(telephone + RedisConstant.SENDTYPE_ORDER, 5 * 60, validateCode.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //生成四位验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        /*try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode.toString());
        } catch (ClientException e) {
            //todo 异常处理
            e.printStackTrace();
            //验证码发送失败
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        System.out.println("发送的手机验证码为："+validateCode+", 接收手机号："+telephone);
        //将生成的验证码存储到redis中 5分钟后失效
        jedisPool.getResource().setex(telephone + RedisConstant.SENDTYPE_LOGIN, 5 * 60, validateCode.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


}
