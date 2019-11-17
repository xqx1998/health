package com.eight.group.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author：xingquanxiang createTime：2019/11/17 17:36
 * description:
 */
@Setter
@Getter
@ToString
public class SetmealReportVO implements Serializable {
    private Integer value;
    private String name;
}
