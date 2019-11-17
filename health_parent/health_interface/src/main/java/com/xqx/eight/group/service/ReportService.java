package com.xqx.eight.group.service;

import com.eight.group.vo.SetmealReportVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author： xingquanxiang
 * createTime：2019/11/17 18:38
 * description:
 */
public interface ReportService {
    /**
     * 根据月份统计会员数量
     * @param months
     * @return
     */
    List<Integer> findMemberCountByMonth(List<String> months);

    /**
     * 统计每个套餐预约数量
     * @return
     */
    List<SetmealReportVO> findOrderCountBySetmeal();

    /**
     * 统计运营数据
     * 新增会员数, 总会员数, 本周新增会员数, 本月新增会员数
     * 今日预约数	, 今日到诊数
     * 本周预约数	, 本周到诊数
     * 本月预约数	, 本月到诊数
     * 热门套餐（套餐名称、预约数量、占比）
     * @return
     */
    Map<String, Object> getBusinessReportData();
}
