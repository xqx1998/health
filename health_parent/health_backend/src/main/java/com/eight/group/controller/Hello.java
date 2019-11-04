package com.eight.group.controller;

import java.util.Objects;

/**
 * @author：xingquanxiang createTime：2019/11/3 15:49
 * description:
 */
public class Hello {
    public static void main(String[] args) {
        String a = null;
        // System.out.println(a.equals("a"));
        System.out.println(Objects.equals(a,null));
        int b = 4;
        System.out.println(Objects.toString(a,"a为空"));
    }
}
