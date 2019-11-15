package com.xqx.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：xingquanxiang createTime：2019/11/15 11:29
 * description:
 */
@RestController
public class UserController {

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('add')") //调用此方法要求当前用户必须具有add权限
    public String add(){
        System.out.println("add....");
        return "success";
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //调用此方法要求当前用户必须具有ROLE_ADMIN角色
    public String delete(){
        System.out.println("delete....");
        return "success";
    }
}
