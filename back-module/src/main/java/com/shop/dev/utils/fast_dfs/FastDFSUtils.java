package com.shop.dev.utils.fast_dfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

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
    public static String imageHandler(String propertiesPath,byte[] content,String ext) {
        try {
            String property = MyPropertyUtils.getProperty(propertiesPath, "fastdfs.tracker_servers");
            String url = "http://" + property.split(":")[0]+":8080";
            ClientGlobal.initByProperties(propertiesPath);

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            String[] strings = storageClient.upload_file(content, ext, null);

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
