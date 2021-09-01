package com.ww.mqtt;

import com.ww.callback.ReconnectionCallback;
import com.ww.help.ServiceProvider;
import com.ww.listener.BaseListener;
import com.ww.util.MqttPropertiesHelper;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/27 13:42
 * @description：mqtt客户端
 */
public class MQTTClient {

    private final static Logger log = LoggerFactory.getLogger(MQTTClient.class);

    public final static int SUCCESS = 200;
    private MqttProperties mqttProperties;
    private MqttClient client;

    public void setMqttProperties(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    public MQTTClient() {

    }

    public MQTTClient(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    public static void main(String[] args) throws InterruptedException {
        MQTTClient mqttClient = new MQTTClient(MqttPropertiesHelper.mqttProperties());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        mqttClient.connect();
        countDownLatch.await();
    }

    public int connect() {
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            client = new MqttClient(mqttProperties.getBroker(), StringUtils.isEmpty(mqttProperties.getClientId()) ? System.getenv().get("COMPUTERNAME") : mqttProperties.getClientId(), persistence);

            //MQTT连接选项
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(mqttProperties.getUserName());
            connectOptions.setPassword(StringUtils.isEmpty(mqttProperties.getPassword()) ? "".toCharArray() : mqttProperties.getPassword().toCharArray());
            //保留会话
            connectOptions.setCleanSession(true);

            //设置重连回调
            client.setCallback(new ReconnectionCallback(this));

            //建立连接
            log.info("建立连接：{}", mqttProperties.getBroker());
            client.connect(connectOptions);
            log.info("连接成功");

            //循环订阅
            List<BaseListener> listeners = ServiceProvider.provideByJavaSpi(BaseListener.class);
            for (BaseListener listener : listeners) {
                client.subscribe(listener.getTopic(), listener);
            }
        } catch (MqttException e) {
            printEx(e);
            e.printStackTrace();
            client = null;
            return e.getReasonCode();
        }
        return MQTTClient.SUCCESS;
    }

    public void publishMsg(String content) {
        log.info("发布消息：{}", content);
        MqttMessage mqttMessage = new MqttMessage(content.getBytes());
        mqttMessage.setQos(mqttProperties.getQos());
        try {
            client.publish(mqttProperties.getPubTopic(), mqttMessage);
            log.info("消息发送完成");
        } catch (MqttException e) {
            printEx(e);
            e.printStackTrace();
        }
    }

    private void printEx(MqttException e) {
        log.info("reason:{}", e.getReasonCode());
        log.info("msg:{}", e.getMessage());
        log.info("loc:{}", e.getLocalizedMessage());
        log.info("cause:{}", e.getCause());
        log.info("excep:{}", e);
    }

    public void disconnectAndClose() {
        try {
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            printEx(e);
            e.printStackTrace();
        }
    }

    public void tryToConnect() {
        //关闭之前的连接
        if (this.client != null) {
            this.disconnectAndClose();
            client = null;
        }
        boolean connected = false;
        while (!connected) {
            if (MQTTClient.SUCCESS == connect()) {
                connected = true;
                log.info("连接成功");
            } else {
                try {
                    log.info("连接失败");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
