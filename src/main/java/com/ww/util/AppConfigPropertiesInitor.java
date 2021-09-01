package com.ww.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/28 19:54
 * @description：系统配置初始化
 */
@Slf4j
public class AppConfigPropertiesInitor {

    public static Map initProperties(String FilePathPropertyName) throws IOException {
        String path;
        Properties properties = System.getProperties();
        path = properties.getProperty(FilePathPropertyName);
        log.info("path:{}", path);
        if (path == null) {
            path = MqttPropertiesHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(0, path.lastIndexOf("/") + 1);
        }
        log.info("jar path:{}", path);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path + "MqttProperties.properties")));
        Properties pro = new Properties();
        pro.load(inputStream);
        Map<Object, Object> propertiesMap = new HashMap<>();

        //加载系统的变量
        for (Map.Entry<Object, Object> objectObjectEntry : pro.entrySet()) {
            log.info("key:{} value:{}", objectObjectEntry.getKey(), objectObjectEntry.getValue());
            propertiesMap.put(objectObjectEntry.getKey(), objectObjectEntry.getValue());
            //将配置文件中的配置加载至系统变量中
            if (!properties.contains(objectObjectEntry.getKey())) {
                properties.put(objectObjectEntry.getKey(), objectObjectEntry.getValue());
            }
        }
        //同名变量中系统优先级更高，对map中的同名变量进行替换
        for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
            log.info("key:{} value:{}", objectObjectEntry.getKey(), objectObjectEntry.getValue());
            if (propertiesMap.containsKey(objectObjectEntry.getKey())) {
                propertiesMap.replace(objectObjectEntry.getKey(), objectObjectEntry.getValue());
            }
        }
        return propertiesMap;

    }
}
