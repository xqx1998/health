package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.Result;
import com.eight.group.pojo.OrderSetting;
import com.eight.group.utils.POIUtils;
import com.xqx.eight.group.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * @author：xingquanxiang createTime：2019/11/9 16:14
 * description:
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    /**
     * Excel文件上传，并解析文件内容保存到数据库
     *
     * @param excelFile 上传的Excel文件
     * @return Result
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            //读取exce文件 用list存储
            List<String[]> list = POIUtils.readExcel(excelFile);
            //判断数据是否为空
            if (list != null && list.size() > 0) {
                //
                ArrayList<OrderSetting> orderSettings = new ArrayList<>();
                for (String[] strings : list) {
                    orderSettings.add(new OrderSetting(new Date(simpleDateFormat.parse(strings[0]).getTime()), Integer.parseInt(strings[1])));
                }
                //调用服务层批量将预约设置数据写入数据库
                orderSettingService.add(orderSettings);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    /**
     * 根据月份获取 预约数据
     *
     * @param date 年-月
     * @return Result
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) {
        try {
            //获取预约设置数据成功
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderSettingService.getOrderSettingByMonth(date));
        } catch (Exception e) {
            //获取预约设置数据失败
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    /**
     * 根据指定日期修改可预约人数
     *
     * @param orderSetting
     * @return　Result
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //预约设置失败
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }


}
