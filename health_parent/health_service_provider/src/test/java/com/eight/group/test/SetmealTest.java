package com.eight.group.test;

import com.eight.group.mapper.SetmealMapper;
import com.eight.group.pojo.Setmeal;
import com.xqx.eight.group.service.SetmealService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/11 18:11
 * description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml","classpath:spring-tx.xml"})
public class SetmealTest {
    @Autowired
    private SetmealMapper setmealMapper;

    @Test
    public void test1(){
        Setmeal byIdDetail = setmealMapper.findByIdDetail(13);
        System.out.println("byIdDetail = " + byIdDetail);

        Setmeal byId = setmealMapper.findById(13);
        System.out.println("byId = " + byId);
    }
}
