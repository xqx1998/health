package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.Result;
import com.eight.group.utils.DateUtils;
import com.eight.group.vo.SetmealReportVO;
import com.xqx.eight.group.service.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author：xingquanxiang createTime：2019/11/17 11:29
 * description:
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private ReportService reportService;


    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        try {
            HashMap<String, Object> map = new HashMap<>();
            ArrayList<String> months = new ArrayList<>(12);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -12);
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH, 1);
                months.add(DateUtils.parseDate2String(calendar.getTime(), "yyyy-MM"));
            }
            map.put("months", months);
            List<Integer> memberCounts = reportService.findMemberCountByMonth(months);
            map.put("memberCounts", memberCounts);
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        try {
            List<SetmealReportVO> setmealReportVOLsit = reportService.findOrderCountBySetmeal();
            HashMap<String, Object> map = new HashMap<>();
            ArrayList<String> setmealNames = new ArrayList<>();
            for (SetmealReportVO vo : setmealReportVOLsit) {
                setmealNames.add(vo.getName());
            }
            map.put("setmealNames", setmealNames);
            map.put("setmealCounts", setmealReportVOLsit);
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
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
    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_SUCCESS,
                    reportService.getBusinessReportData());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

}
