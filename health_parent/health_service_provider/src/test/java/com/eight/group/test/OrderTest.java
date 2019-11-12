package com.eight.group.test;

import com.eight.group.mapper.MemberMapper;
import com.eight.group.mapper.OrderMapper;
import com.eight.group.mapper.OrderSettingMapper;
import com.eight.group.pojo.Member;
import com.eight.group.pojo.Order;
import com.eight.group.pojo.OrderSetting;
import com.eight.group.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/12 18:44
 * description:
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration({"classpath:spring-dao.xml","classpath:spring-tx.xml"})
public class OrderTest {
   /* @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testFindByDateWitOrderSetting() throws Exception {
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate("2019-11-12");
        System.out.println(orderSetting);
    }

    @Test
    public void testFindByTelephoneWithMember() throws Exception {
        Member member = memberMapper.findByTelephone("13529361695");
        System.out.println(member);
    }

    @Test
    public void testFindByConditionWithOrder() throws Exception {
        List<Order> orderList = orderMapper.findByCondition(new Order(84, new Date(DateUtils.parseString2Date("2019-04-28").getTime()), 12));
        orderList.forEach(System.out::println);
    }

    @Test
    public void tesAddMember() throws Exception {
        //4.检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
        Member member = new Member();
        member.setName("小明");
        member.setPhoneNumber("12345678900");
        member.setIdCard("123456789012345678");
        member.setSex("1");
        member.setRegTime(new Date(System.currentTimeMillis()));
        memberMapper.add(member);//自动完成会员注册
    }

    @Test
    public void testEditReservationsByOrderDate() throws Exception {
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate("2019-11-12");
        orderSetting.setReservations(orderSetting.getReservations()+1);
        //4.检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
        orderSettingMapper.editReservationsByOrderDate(orderSetting);
    }
*/

}
