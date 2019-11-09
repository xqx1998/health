package com.eight.group.pojo;
;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

/**
 * 预约设置
 */
@Setter
@Getter
@ToString
public class OrderSetting implements Serializable{
    private Integer id ;
    private Date orderDate;//预约设置日期
    private Integer number;//可预约人数
    private Integer reservations ;//已预约人数

    public OrderSetting(Date orderDate, Integer number) {
        this.orderDate = orderDate;
        this.number = number;
    }

    public OrderSetting(Integer id, Date orderDate, Integer number, Integer reservations) {
        this.id = id;
        this.orderDate = orderDate;
        this.number = number;
        this.reservations = reservations;
    }
}
