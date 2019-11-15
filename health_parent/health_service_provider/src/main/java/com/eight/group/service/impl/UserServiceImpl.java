package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.mapper.UserMapper;
import com.eight.group.pojo.User;
import com.xqx.eight.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author：xingquanxiang createTime：2019/11/3 21:40
 * description:
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
