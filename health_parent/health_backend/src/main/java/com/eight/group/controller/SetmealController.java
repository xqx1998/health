package com.eight.group.controller;
import	java.lang.annotation.Retention;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.constant.RedisConstant;
import com.eight.group.entity.PageResult;
import com.eight.group.entity.QueryPageBean;
import com.eight.group.entity.Result;
import com.eight.group.pojo.Setmeal;
import com.eight.group.utils.LocalPictureUtils;
import com.eight.group.utils.QiniuUtils;
import com.eight.group.utils.UuidUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.xqx.eight.group.service.SetmealService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Objects;

/**
 * @author：xingquanxiang createTime：2019/11/8 11:12
 * description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 图片上传
     * @param imgFile 图片文件
     * @return Result
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        String imgFileName = imgFile.getOriginalFilename();
        //改名
        imgFileName = UuidUtils.getUuid() + imgFileName.substring(imgFileName.lastIndexOf("."));
        try {
            LocalPictureUtils.upload(imgFileName, imgFile.getBytes());
        } catch (IOException e) {
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        //将图片名称添加到redis集合中  setmealPicResources
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, imgFileName);
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, imgFileName);
    }

    /**
     * 添加套餐
     * @param setmeal  套餐信息
     * @param checkgroupIds 套餐关联检查组id们
     * @return  Result
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        if (setmeal.getCode()!=null) {
            try {
                setmealService.add(setmeal, checkgroupIds);
            } catch (Exception e) {
                return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
            }
            //将添加到数据库中的图片名称存到redis集合中   setmealPicDbResources
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        }else{
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, setmealService.findPage(queryPageBean));
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        if (id!=null) {
            try {
                return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealService.findById(id));
            } catch (Exception e) {
                return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
            }
        } else {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        // todo 在修改之前查出数据库中套餐信息
        Setmeal oldSetmeal = setmealService.findById(setmeal.getId());
        if (setmeal.getCode()!=null) {
            try {
                setmealService.edit(setmeal, checkgroupIds);
            } catch (Exception e) {
                return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
            }
            //todo 判断图片是否已经更改 是 则删除图片服务器中之前的图片文件 删除redis中之前的图片名称数据
            if(oldSetmeal.getImg()!=null && !oldSetmeal.equals(setmeal.getImg())){
                //将之前的图片名称在redis集合中删除   setmealPicDbResources
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, oldSetmeal.getImg());
            }
            //将添加到数据库中的图片名称存到redis集合中   setmealPicDbResources
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
        }else{
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        if (id!=null) {
            try {
                setmealService.delete(id);
            } catch (Exception e) {
                return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
            }
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } else {
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }
}
