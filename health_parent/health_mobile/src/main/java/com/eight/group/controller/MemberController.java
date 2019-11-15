package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.eight.group.constant.MessageConstant;
import com.eight.group.constant.RedisConstant;
import com.eight.group.entity.Result;
import com.eight.group.pojo.Member;
import com.xqx.eight.group.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Map;

/**
 * @author：xingquanxiang createTime：2019/11/13 23:24
 * description:
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 手机号快速登录
     *
     * @param map
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestBody Map map, HttpServletResponse response) {
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        //从redis中获取验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisConstant.SENDTYPE_LOGIN);
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //验证码输入正确
            //判断当前用户是否为会员，自动完成注册
            Member member = memberService.findByTelephone(telephone);
            if (member == null) {
                //不是会员，自动完成注册
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date(System.currentTimeMillis()));
                memberService.add(member);
            }
            //向客户端写入cookis，内容为手机号
            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setPath("/");
            //设置cookie有效期 为1周
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            //将会员信息保存到Redis中
            jedisPool.getResource().setex(telephone, 60 * 30, JSON.toJSON(member).toString());
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        } else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
}
