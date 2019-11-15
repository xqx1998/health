package com.eight.group.mapper;

import com.eight.group.pojo.User;

/**
 * @author： xingquanxiang
 * createTime：2019/11/3 21:49
 * description:
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
