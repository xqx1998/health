package com.xqx.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.pojo.TUser;
import com.xqx.eight.group.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @author：xingquanxiang createTime：2019/11/15 9:34
 * description: 密码明文表示
 */
public class SpringSecurityUserService2 implements UserDetailsService {
    @Reference
    private UserService userService;
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户输入的用户名:"+username);
        //根据用户名查询数据库获得用户信息
        TUser dbUser = userService.findByUsername(username);
        if (dbUser == null){
            return null;
        }
        //将用户信息返回给框架
        //矿建进行密码比对
        //创建权限列表
        ArrayList<GrantedAuthority> grantedAuthorityArrayList = new ArrayList<>();
        //授予权限
        grantedAuthorityArrayList.add(new SimpleGrantedAuthority("permission_A"));
        grantedAuthorityArrayList.add(new SimpleGrantedAuthority("permission_B"));
        //授予角色
        grantedAuthorityArrayList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(dbUser.getUsername(), "{noop}"+dbUser.getPassword(), grantedAuthorityArrayList);
    }
}
