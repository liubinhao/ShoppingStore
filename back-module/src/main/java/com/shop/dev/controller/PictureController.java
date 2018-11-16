package com.shop.dev.controller;

import com.shop.dev.utils.FastDFSUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PictureController
 * @Author 刘树青
 * @Date 2018/11/16 8:40
 * @Version 1.0
 */
@RestController
public class PictureController {
    @RequestMapping("/pic/upload")
    public Map uploadPic(MultipartFile uploadFile) {
        try {
            // 首先接收页面上传的文件
            byte[] content = uploadFile.getBytes();
            // 取出文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 把这个文件内容上传到图片服务器
            String url = FastDFSUtils.imageHandler("fastdfs.properties", content, ext);
            System.out.println(url);
            // 从配置文件中取图片服务器的url
            // 创建返回结果对象
            Map result = new HashMap();
            result.put("error", 0);
            result.put("url", url);
            // 返回结果
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return result;
        }
    }
}
