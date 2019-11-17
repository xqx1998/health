package com.xqx.eight.group.service;

import com.eight.group.pojo.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/13 23:44
 * description:
 */
public interface MemberService {
    /**
     * 根据手机号查询会员信息
     * @param telephone
     * @return Member
     */
    Member findByTelephone(String telephone);

    /**
     * 新增会员
     * @param member
     */
    void add(Member member);

}
