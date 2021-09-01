package com.ww.listener;

import com.alibaba.fastjson.JSONObject;
import com.ww.model.common.MqttConnectedDTO;
import com.ww.service.impl.OnlineMailService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/29 17:12
 * @description：连接监听
 */
@Slf4j
public class ConnectedListener implements BaseListener {

    private final static String CONNECTED = "$SYS/brokers/+/clients/+/connected";

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //subscribe后得到的消息会执行到这里面
        log.info("接收消息主题:{}", topic);
        log.info("接收消息Qos:{}", message.getQos());
        log.info("接收消息内容:{}", new String(message.getPayload()));
        MqttConnectedDTO mqttConnectedDTO = JSONObject.parseObject(new String(message.getPayload()), MqttConnectedDTO.class);
        //发送邮件通知
        try {
            OnlineMailService onlineMailService = new OnlineMailService();
            onlineMailService.send(topic, message, mqttConnectedDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getTopic() {
        return ConnectedListener.CONNECTED;
    }
}
