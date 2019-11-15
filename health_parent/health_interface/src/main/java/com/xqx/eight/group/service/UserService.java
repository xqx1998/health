package com.xqx.eight.group.service;

import com.eight.group.pojo.User;

/**
 * @author：xingquanxiang createTime：2019/11/3 21:39
 * description:
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return User
     */
    User findByUsername(String username);
}
