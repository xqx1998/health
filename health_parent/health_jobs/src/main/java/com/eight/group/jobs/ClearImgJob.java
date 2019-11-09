package com.eight.group.jobs;

import com.eight.group.constant.RedisConstant;
import com.eight.group.utils.LocalPictureUtils;
import com.eight.group.utils.QiniuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Locale;
import java.util.Set;
/**
 * 自定义Job，实现定时清理垃圾图片
 * @author：xingquanxiang createTime：2019/11/9 11:12
 * description:
 */
public class ClearImgJob {
    private static Logger log = LoggerFactory.getLogger(ClearImgJob.class);
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null & set.size()>0){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                // QiniuUtils.deleteFileFromQiniu(picName);
                try {
                    LocalPictureUtils.detele(picName);
                } catch (Exception e) {
                    log.info("删除图片："+picName + " || 失败");
                }
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
                System.out.println("自定义任务执行，清理垃圾图片:" + picName);
            }
        }
    }
}

