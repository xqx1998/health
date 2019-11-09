package com.eight.group.pojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import	java.io.Serializable;

/**
 * @author：xingquanxiang createTime：2019/11/3 20:57
 * description:
 */
@Setter
@Getter
@ToString
public class TUser implements Serializable{
    private Integer id;
    private String username;
    private String password;

}
