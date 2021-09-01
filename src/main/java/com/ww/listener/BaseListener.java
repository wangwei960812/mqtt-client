package com.ww.listener;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public interface BaseListener extends IMqttMessageListener {
    String getTopic();
}
