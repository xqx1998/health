package com.eight.group.test;

import com.eight.group.pojo.TUser;
import com.xqx.eight.group.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author：xingquanxiang createTime：2019/11/3 22:02
 * description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml","classpath:spring-service.xml","classpath:spring-tx.xml"})
public class UserServiceImpTest {
    @Autowired
    private UserService userService;

    @Test
    public void testLogin(){
        TUser TUser = new TUser();
        TUser.setUsername("xqx");
        TUser.setPassword("1998");
        boolean login = userService.login(TUser);
        System.out.println("login = " + login);
    }
}
