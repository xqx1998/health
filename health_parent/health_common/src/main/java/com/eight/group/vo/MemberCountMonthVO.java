package com.eight.group.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author：xingquanxiang createTime：2019/11/17 14:57
 * description:
 */
@Setter
@Getter
@ToString
public class MemberCountMonthVO implements Serializable {
    private String month;
    private Integer count;
}
