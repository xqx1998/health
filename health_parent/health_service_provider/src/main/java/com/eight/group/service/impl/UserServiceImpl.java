package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.mapper.UserMapper;
import com.eight.group.pojo.User;
import com.xqx.eight.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author：xingquanxiang createTime：2019/11/3 21:40
 * description:
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;
    @Override
    public boolean login(User user) {
        return userMapper.getUserByUsernameAndPassword(user)!=null;
    }
}
