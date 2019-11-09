package com.eight.group.utils;
import	java.util.UUID;

/**
 * @author：xingquanxiang createTime：2019/11/8 18:42
 * description:
 */
public class UuidUtils {
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
