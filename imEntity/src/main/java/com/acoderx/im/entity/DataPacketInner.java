package com.acoderx.im.entity;

import java.io.Serializable;

/**
 * Created by xiaobaibai on 2017/2/9.
 */
public class DataPacketInner implements Serializable {
    private String sessionID;
    private String targetId;
    private DataPacket message;

    public DataPacketInner(){}
    public DataPacketInner(String sessionID, String targetId, DataPacket message) {
        this.sessionID = sessionID;
        this.targetId = targetId;
        this.message = message;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public DataPacket getMessage() {
        return message;
    }

    public void setMessage(DataPacket message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DataPacketInner{" +
                "sessionID='" + sessionID + '\'' +
                ", targetId='" + targetId + '\'' +
                ", message=" + message +
                '}';
    }
}
