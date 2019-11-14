package com.eight.group.vo;

import com.eight.group.pojo.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


/**
 * @author：xingquanxiang createTime：2019/11/13 20:27
 * description:
 */
@Setter
@Getter
@ToString
public class OrderVO extends Order {
    private String member;
    private String setmeal;

    public OrderVO() {}

    public OrderVO(Date orderDate, String orderType, String member, String setmeal) {
        super(orderDate, orderType);
        this.member = member;
        this.setmeal = setmeal;
    }
}
