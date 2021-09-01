package com.ww.util;

import com.ww.mqtt.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/28 19:33
 * @description：配置帮助类
 */
@Slf4j
public class MqttPropertiesHelper {

    public final static String FILE_PATH_PROPERTY_NAME = "MqttProperties.path";
    private final static MqttProperties mqttProperties = new MqttProperties();

    static {
        try {
            BeanUtils.populate(mqttProperties, AppConfigPropertiesInitor.initProperties(FILE_PATH_PROPERTY_NAME));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MqttProperties mqttProperties() {
        return mqttProperties;
    }

}
