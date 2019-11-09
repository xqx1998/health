package com.eight.group.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * @author：xingquanxiang createTime：2019/11/9 11:39
 * description:
 */
public class LocalPictureUtils {
    private static String uploadPath = QiniuUtils.uploadURL;

    /**
     * 创建客户端
     */
    private static Client clientUpload = Client.create();
    private static Client clientupDelete = Client.create();

    /**
     *
     * @param imgFileName
     * @param imgFileBytes
     */
    public static void upload(String imgFileName, byte[] imgFileBytes){
        //指定上传路径与文件名
        WebResource resource = clientUpload.resource(uploadPath + imgFileName);
        //上传（其本质就是文件以字节流的形式重新写入到其他服务器）
        resource.put(imgFileBytes);
    }


    public static void detele(String imgFileName){
        try {
            //指定上传路径与文件名
            WebResource resource = clientupDelete.resource(uploadPath + imgFileName);
            //上传（其本质就是文件以字节流的形式重新写入到其他服务器）
            resource.delete();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
