package com.ww.mqtt;

import com.ww.util.IpUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/28 15:09
 * @description：mqtt客户端配置类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttProperties {
    private String subTopic;
    private String pubTopic;
    private String broker;
    /**
     * 默认为ip
     */
    private String clientId;
    /**
     * 默认为2
     */
    private int qos = 2;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
