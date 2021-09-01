/**
 * Copyright 2021 bejson.com
 */
package com.ww.model.common;

/**
 * Auto-generated: 2021-08-29 16:33:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MqttDisconnectedDTO {

    private String username;
    private long ts;
    private int sockport;
    private String reason;
    private int proto_ver;
    private String proto_name;
    private String ipaddress;
    private long disconnected_at;
    private String clientid;
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

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
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

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    public String getIpaddress() {
        return ipaddress;
    }

    public void setDisconnected_at(long disconnected_at) {
        this.disconnected_at = disconnected_at;
    }
    public long getDisconnected_at() {
        return disconnected_at;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public String getClientid() {
        return clientid;
    }

}