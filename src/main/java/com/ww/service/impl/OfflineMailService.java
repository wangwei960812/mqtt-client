package com.ww.service.impl;

import com.ww.mail.model.dto.req.SimpleMailMessageDTO;
import com.ww.model.common.MqttDisconnectedDTO;
import com.ww.service.abs.BaseMailService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/29 17:45
 * @description：下线邮件通知服务
 */
public class OfflineMailService extends BaseMailService {

    @Override
    protected SimpleMailMessageDTO assembleMailMessage(Object... params) {
        String from = System.getProperty("mail.from");
        String to = System.getProperty("mail.to");
        MqttDisconnectedDTO mqttDisconnectedDTO = (MqttDisconnectedDTO) params[2];
        SimpleMailMessageDTO simpleMailMessageDTO = new SimpleMailMessageDTO();
        simpleMailMessageDTO.setFrom(from);
        simpleMailMessageDTO.setSubject("设备下线通知");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String sd = sdf.format(mqttDisconnectedDTO.getDisconnected_at());
        String text = "设备：" + mqttDisconnectedDTO.getClientid() + " 在ip地址：" + mqttDisconnectedDTO.getIpaddress() + " 下线，下线时间：" + sd;
        simpleMailMessageDTO.setText(text);
        String[] tos = to.split(",");
        List<String> toList = Arrays.asList(tos);
        simpleMailMessageDTO.setTo(toList);
        return simpleMailMessageDTO;
    }
}
