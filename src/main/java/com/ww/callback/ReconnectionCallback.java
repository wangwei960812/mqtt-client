package com.ww.callback;

import com.ww.mqtt.MQTTClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/27 14:02
 * @description：消息回调
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReconnectionCallback implements MqttCallback {

    private MQTTClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        //连接丢失后，在这里进行重连
        log.info("连接丢失，进行重连");
        client.tryToConnect();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("接收消息主题:{}", s);
        log.info("接收消息Qos:{}", mqttMessage.getQos());
        log.info("接收消息内容:{}", new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete---------{}", iMqttDeliveryToken.isComplete());
    }
}
