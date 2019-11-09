package com.eight.group.constant;

public class RedisConstant {
    //套餐图片所有图片名称
    public static final String SETMEAL_PIC_RESOURCES = "setmealPicResources";
    //套餐图片保存在数据库中的图片名称
    public static final String SETMEAL_PIC_DB_RESOURCES = "setmealPicDbResources";
    //预约设置 插入时在redis保存一份日期用作查询已存在 则更新
    public static final String ORDER_SETTING_DB_ORDER_DATE = "orderSettingDbOrderDate";

}
