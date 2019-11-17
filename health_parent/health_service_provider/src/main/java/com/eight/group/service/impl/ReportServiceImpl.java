package com.eight.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.eight.group.mapper.ReportMapper;
import com.eight.group.utils.DateUtils;
import com.eight.group.vo.MemberCountMonthVO;
import com.eight.group.vo.SetmealReportVO;
import com.xqx.eight.group.service.ReportService;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author：xingquanxiang createTime：2019/11/17 18:40
 * description:
 */
@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    /**
     * 根据月份统计会员数量
     *
     * @param months
     * @return
     */
    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        //查询当前日期至前12个月来的会员数量
        List<MemberCountMonthVO> memberCountByMonth =
                reportMapper.findMemberCountByMonth(months.get(0) + "-01");
        //创建list存储每个月的会员数量
        List<Integer> counts = new ArrayList<>();
        //判断当月是否已经填充
        boolean flag;
        //每月会员数量填充
        for (String month : months) {
            //初始化 未填充
            flag = false;
            for (MemberCountMonthVO monthVO : memberCountByMonth) {
                if (month.equals(monthVO.getMonth())){
                    counts.add(monthVO.getCount());
                    //已填充
                    flag = true;
                    break;
                }
            }
            //判断是否没有填充
            if (!flag){
                // 是没有填充 自动填充为0
                counts.add(0);
            }
        }
        return counts;
    }

    /**
     * 统计每个套餐预约数量
     *
     * @return
     */
    @Override
    public List<SetmealReportVO> findOrderCountBySetmeal() {
        return reportMapper.findOrderCountBySetmeal();
    }


    /**
     * 统计运营数据
     * 新增会员数, 总会员数, 本周新增会员数, 本月新增会员数
     * 今日预约数	, 今日到诊数
     * 本周预约数	, 本周到诊数
     * 本月预约数	, 本月到诊数
     * 热门套餐（套餐名称、预约数量、占比）
     * @return
     */
    @Override
    public Map<String, Object> getBusinessReportData() {
        /**
         * 统计运营数据
         * 新增会员数, 总会员数, 本周新增会员数, 本月新增会员数
         * 今日预约数	, 今日到诊数
         * 本周预约数	, 本周到诊数
         * 本月预约数	, 本月到诊数
         */
        Map<String, Object> map = reportMapper.getBusinessReportData();
        // 统计热门套餐（套餐名称、预约数量、占比）
        List<Map<String, String>> hotSetmealList = reportMapper.getHotSetmeal(3);
        map.put("hotSetmeal", hotSetmealList);
        return map;
    }
}
