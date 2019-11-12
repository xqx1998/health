package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.Result;
import com.eight.group.mapper.MemberMapper;
import com.eight.group.mapper.OrderMapper;
import com.eight.group.mapper.OrderSettingMapper;
import com.eight.group.pojo.Member;
import com.eight.group.pojo.Order;
import com.eight.group.pojo.OrderSetting;
import com.eight.group.utils.DateUtils;
import com.xqx.eight.group.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author：xingquanxiang createTime：2019/11/12 15:31
 * description:
 */
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    /**
     * 体检在线预约
     *
     * @param map 预约客户信息
     * @return Result
     */
    @Override
    public Result order(Map map) throws Exception {
        //获取预约日期
        String orderDate = (String) map.get("orderDate");
        //1.检查用户所选择的预约日期是否已经提前进行了预约设置，若果没有设置就无法进行预约
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate(orderDate);
        if (orderSetting == null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2.检查用户所选择的日期是否已经约满， 若果已经约满则无法进行预约
        int number = orderSetting.getNumber();
        int reservations = orderSetting.getReservations();
        if (reservations>= number){
            //已经约满，无法预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        //3.检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
        String telephone = (String) map.get("telephone");
        Member member = memberMapper.findByTelephone(telephone);
        if (member != null){
            //判断是否在重复预约
            Integer memberId = member.getId();
            Date date = new Date(DateUtils.parseString2Date(orderDate).getTime());
            String setmealId = (String) map.get("setmealId");
            //根据条件查询
            List<Order> orderList = orderMapper.findByCondition(new Order(memberId, date, Integer.parseInt(setmealId)));
            if (orderList != null && orderList.size()>0){
                //说明用户在重复预约，无法再次完成预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }else{
            //4.检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date(System.currentTimeMillis()));
            memberMapper.add(member);//自动完成会员注册
        }
        //预约成功，更新当日的与预约人数
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(new Date(DateUtils.parseString2Date(orderDate).getTime()));
        order.setOrderType((String)map.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt((String)map.get("setmealId")));
        try {
            //添加预约
            orderMapper.add(order);
            //设置已预约人数 +1
            orderSetting.setReservations(orderSetting.getReservations()+1);
            orderSettingMapper.editReservationsByOrderDate(orderSetting);
        } catch (Exception e) {
            return new Result(true, MessageConstant.ORDER_FAIL);
        }
        return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
    }
}
