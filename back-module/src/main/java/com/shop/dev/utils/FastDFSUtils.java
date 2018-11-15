package com.shop.dev.utils;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName FastDFSUtils
 * @Author 刘树青
 * @Date 2018/11/13 15:56
 * @Version 1.0
 */
public class FastDFSUtils {

    /**
     * @Author 刘树青 用于返回图片地址
     * @Date 2018/11/13 16:34
     * @param: [propertiesPath:配置文件路径
     * , url:图片路径前缀
     * , path: 上传文件路径]
     * return: java.lang.String
     */
    public static String imageHandler(String propertiesPath, String path) {
        try {
            String property = MyPropertyUtils.getProperty(propertiesPath, "fastdfs.tracker_servers");
            String url = "http://" + property.split(":")[0]+":8080";
            ClientGlobal.initByProperties(propertiesPath);

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            byte[] bytes = IOUtils.toByteArray(new FileInputStream(new File(path)));
            String[] strings = storageClient.upload_file(bytes, 0, bytes.length, "jpg", null);

            String imagePath = url + "/" + strings[0] + '/' + strings[1];
            return imagePath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

}
