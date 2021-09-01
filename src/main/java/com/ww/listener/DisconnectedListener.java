package com.ww.listener;

import com.alibaba.fastjson.JSONObject;
import com.ww.model.common.MqttDisconnectedDTO;
import com.ww.service.impl.OfflineMailService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/29 17:12
 * @description：连接监听
 */
@Slf4j
public class DisconnectedListener implements BaseListener {

    public final static String DISCONNECTED = "$SYS/brokers/+/clients/+/disconnected";

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //subscribe后得到的消息会执行到这里面
        log.info("接收消息主题:{}", topic);
        log.info("接收消息Qos:{}", message.getQos());
        log.info("接收消息内容:{}", new String(message.getPayload()));
        MqttDisconnectedDTO mqttDisconnectedDTO = JSONObject.parseObject(new String(message.getPayload()), MqttDisconnectedDTO.class);
        //发送邮件通知
        try {
            OfflineMailService offlineMailService = new OfflineMailService();
            offlineMailService.send(topic, message, mqttDisconnectedDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getTopic() {
        return DisconnectedListener.DISCONNECTED;
    }
}
