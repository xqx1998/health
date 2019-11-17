package com.eight.group.mapper;

import com.eight.group.vo.MemberCountMonthVO;
import com.eight.group.vo.SetmealReportVO;

import java.util.List;
import java.util.Map;

/**
 * @author： xingquanxiang
 * createTime：2019/11/17 18:42
 * description:
 */
public interface ReportMapper {
    /**
     * 根据月份查询会员总数
     * @param beginDate
     * @return
     */
    List<MemberCountMonthVO> findMemberCountByMonth(String beginDate);

    /**
     * 统计每个套餐预约数量
     * @return
     */
    List<SetmealReportVO> findOrderCountBySetmeal();

    /**
     * 统计运营数据
     * @return
     */
    Map<String, Object> getBusinessReportData();

    /**
     * 统计热门套餐
     * @param limitCount
     * @return
     */
    List<Map<String, String>> getHotSetmeal(int limitCount);
}
