package com.shop.dev.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName MyPropertyUtils
 * @Author 刘树青
 * @Date 2018/11/13 16:59
 * @Version 1.0
 */
public class MyPropertyUtils {
    //获取properties中的key相对的value 值
    public static String getProperty(String pro, String key) {
        Properties properties = new Properties();

        //获取流
        InputStream resourceAsStream = MyPropertyUtils.class.getClassLoader().getResourceAsStream(pro);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty(key);
        return property;
    }
}
