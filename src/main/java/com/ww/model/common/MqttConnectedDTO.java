/**
 * Copyright 2021 bejson.com
 */
package com.ww.model.common;

/**
 * Auto-generated: 2021-08-29 16:34:42
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MqttConnectedDTO {

    private String username;
    private long ts;
    private int sockport;
    private int proto_ver;
    private String proto_name;
    private int keepalive;
    private String ipaddress;
    private int expiry_interval;
    private long connected_at;
    private int connack;
    private String clientid;
    private boolean clean_start;
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
    public long getTs() {
        return ts;
    }

    public void setSockport(int sockport) {
        this.sockport = sockport;
    }
    public int getSockport() {
        return sockport;
    }

    public void setProto_ver(int proto_ver) {
        this.proto_ver = proto_ver;
    }
    public int getProto_ver() {
        return proto_ver;
    }

    public void setProto_name(String proto_name) {
        this.proto_name = proto_name;
    }
    public String getProto_name() {
        return proto_name;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }
    public int getKeepalive() {
        return keepalive;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    public String getIpaddress() {
        return ipaddress;
    }

    public void setExpiry_interval(int expiry_interval) {
        this.expiry_interval = expiry_interval;
    }
    public int getExpiry_interval() {
        return expiry_interval;
    }

    public void setConnected_at(long connected_at) {
        this.connected_at = connected_at;
    }
    public long getConnected_at() {
        return connected_at;
    }

    public void setConnack(int connack) {
        this.connack = connack;
    }
    public int getConnack() {
        return connack;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public String getClientid() {
        return clientid;
    }

    public void setClean_start(boolean clean_start) {
        this.clean_start = clean_start;
    }
    public boolean getClean_start() {
        return clean_start;
    }

}