package com.eight.group.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.mapper.MemberMapper;
import com.eight.group.pojo.Member;
import com.eight.group.vo.MemberCountMonthVO;
import com.xqx.eight.group.service.MemberService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/13 23:46
 * description:
 */
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    /**
     * 根据手机号查询会员信息
     *
     * @param telephone
     * @return Member
     */
    @Override
    public Member findByTelephone(String telephone) {
        return memberMapper.findByTelephone(telephone);
    }

    /**
     * 新增会员
     *
     * @param member
     */
    @Override
    public void add(Member member) {
        memberMapper.add(member);
    }

}
