package com.eight.group.controller;

import com.aliyuncs.exceptions.ClientException;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.Result;
import com.eight.group.utils.SMSUtils;
import com.eight.group.utils.ValidateCodeUtils;
import jdk.nashorn.internal.ir.annotations.Reference;
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
    
    public Result Send4Order(String telephone){
        //生成四位验证码
        Integer templateCode = ValidateCodeUtils.generateValidateCode(4);
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, templateCode.toString());
        } catch (ClientException e) {
            //todo 异常处理
            e.printStackTrace();
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);

    }
}
