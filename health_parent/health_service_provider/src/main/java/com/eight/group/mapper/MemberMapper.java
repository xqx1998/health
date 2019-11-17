package com.eight.group.mapper;

import com.eight.group.pojo.Member;
import com.eight.group.vo.MemberCountMonthVO;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author： xingquanxiang
 * createTime：2019/11/12 19:25
 * description:
 */
public interface MemberMapper {
    /**
     * 根据电话查询会员
     * @param telephone
     * @return Member
     */
    Member findByTelephone(String telephone);

    /**
    /**
     * 添加会员
     * @param member
     */
    void add(Member member);

    /**
     * 查询所有会员
     * @return List<Member>
     */
    List<Member> findAll();

    /**
     * 根据条件分页查询会员
     * @param queryString
     * @return Page<Member>
     */
    Page<Member> selectByCondition(String queryString);

    /**
     * 根据id删除会员信息
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询会员信息
     * @param id
     * @return Member
     */
    Member findById(Integer id);

    /**
     * 更新会员信息
     * @param member
     */
    void edit(Member member);

    /**
     * 根据日期统计会员数， 统计指定日期之前的会员数
     * @param date
     * @return
     */
    Integer findMemberCountBeforeDate(String date);

    /**
     * 根据日期统计某一天注册的会员数
     * @param date
     * @return
     */
    Integer findMemberCountByDate(String date);

    /**
     * 根据日期统计会员数， 统计指定日期之后的会员数
     * @param date
     * @return
     */
    Integer findMemberCountAfterDate(String date);

    /**
     * 查询会员总数
     * @return
     */
    Integer findMemberTotalCount();
}
