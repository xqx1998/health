package com.eight.group.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.pojo.Permission;
import com.eight.group.pojo.Role;
import com.eight.group.pojo.User;
import com.xqx.eight.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author：xingquanxiang createTime：2019/11/15 9:34
 * description: 密码加密表示
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {
    //使用dubbo通过网络远程调用服务提供方获取数据库中的用户信息
    @Reference
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户输入的用户名:"+username);
        System.out.println("passwordEncoder.encode('admin') = " + passwordEncoder.encode("admin"));
        //根据用户名查询数据库获得用户信息
        User user = userService.findByUsername(username);
        System.out.println("user = " + user);
        if (user == null){
            //用户不存在
            return null;
        }
        //将用户信息返回给框架
        //框架进行密码比对
        //动态为当前用户授权
        Set<Role> roles = user.getRoles();
        //创建权限列表
        ArrayList<GrantedAuthority> grantedAuthorityArrayList = new ArrayList<>();
        //遍历授予角色
        for (Role role : roles) {
            grantedAuthorityArrayList.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            //遍历授予权限
            for (Permission permission : permissions) {
                grantedAuthorityArrayList.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        System.out.println("user.getPassword() = " + user.getPassword());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
               user.getPassword(), grantedAuthorityArrayList);
    }
}
