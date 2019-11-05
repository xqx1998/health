package com.eight.group.service.impl;
import	java.awt.Desktop.Action;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.mapper.CheckItemMapper;
import com.eight.group.pojo.CheckItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xqx.eight.group.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：xingquanxiang createTime：2019/11/5 11:54
 * description:
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;
    /**
     * 添加检查项
     *
     * @param checkItem 检查项对象
     */
    @Override
    @Transactional
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return PageResult对象
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页助手参数设置
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //调用mapper查询
        List<CheckItem> list = checkItemMapper.findPage();

        list.forEach(s-> System.out.println(s));
        //放回封装后的结果
        return new PageResult(new PageInfo<>(list).getTotal(),list);
    }

    /**
     * 根据id查询 检查项信息
     *
     * @param id
     * @return CheckItem对象
     */
    @Override
    public CheckItem findById(int id) {
        return checkItemMapper.findById(id);
    }
}