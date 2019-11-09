package com.eight.group.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @author：xingquanxiang createTime：2019/11/7 14:26
 * description:
 */
public class QiniuUtils {
    private static String accessKey;
    private static String secretKey;
    private static String bucket;
    private static String zone;
    private static Configuration cfg;
    private static UploadManager uploadManager;
    private static Auth auth;
    private static String upToken;
    public static String uploadURL;

    static {
        Properties properties = new Properties();
        try {
            properties.load(QiniuUtils.class.getClassLoader().getResourceAsStream("qiniu.properties"));
            accessKey = properties.getProperty("accessKey");
            secretKey = properties.getProperty("secretKey");
            bucket = properties.getProperty("bucket");
            zone = properties.getProperty("zone");
            uploadURL = properties.getProperty("uploadURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构造一个带指定Zone对象的配置类
        try {
            cfg = new Configuration((Zone) Zone.class.getDeclaredMethod(zone, null).invoke(null));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
        auth = Auth.create(accessKey, secretKey);
        upToken = auth.uploadToken(bucket);
    }
    private QiniuUtils() {}

    public static void upload2Qiniu(String filePath, String fileName) {
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //上传文件
    public static void upload2Qiniu(byte[] bytes, String fileName) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet =
                    new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //删除文件
    public static void deleteFileFromQiniu(String fileName) {
        String key = fileName;
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
