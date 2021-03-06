package com.acoderx.im.entity;

/**
 * Created by xudi on 2017/2/9.
 */
public class SessionProperty {
    private String host;
    private int port;
    private String chanelId;

    public SessionProperty(String host, int port, String chanelId) {
        this.host = host;
        this.port = port;
        this.chanelId = chanelId;
    }

    public SessionProperty(String session) {
        String[] results = session.split(":");
        this.host = results[0];
        this.port = Integer.parseInt(results[1]);
        this.chanelId = results[2];
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getChanelId() {
        return chanelId;
    }

    public void setChanelId(String chanelId) {
        this.chanelId = chanelId;
    }

    @Override
    public String toString() {
        return this.host + ":" + this.port + ":" + this.chanelId;
    }
}
