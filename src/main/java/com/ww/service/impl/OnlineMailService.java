package com.ww.service.impl;

import com.ww.mail.model.dto.req.SimpleMailMessageDTO;
import com.ww.model.common.MqttConnectedDTO;
import com.ww.service.abs.BaseMailService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/29 17:34
 * @description：上线邮件通知
 */
public class OnlineMailService extends BaseMailService {
    @Override
    protected SimpleMailMessageDTO assembleMailMessage(Object... params) {
        String from = System.getProperty("mail.from");
        String to = System.getProperty("mail.to");
        MqttConnectedDTO mqttConnectedDTO = (MqttConnectedDTO) params[2];
        SimpleMailMessageDTO simpleMailMessageDTO = new SimpleMailMessageDTO();
        simpleMailMessageDTO.setFrom(from);
        simpleMailMessageDTO.setSubject("设备上线通知");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String sd = sdf.format(mqttConnectedDTO.getConnected_at());
        String text = "设备：" + mqttConnectedDTO.getClientid() + " 在ip地址：" + mqttConnectedDTO.getIpaddress() + " 上线，上线时间：" + sd;
        simpleMailMessageDTO.setText(text);
        String[] tos = to.split(",");
        List<String> toList = Arrays.asList(tos);
        simpleMailMessageDTO.setTo(toList);
        return simpleMailMessageDTO;
    }
}
