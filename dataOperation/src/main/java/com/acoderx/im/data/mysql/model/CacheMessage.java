package com.acoderx.im.data.mysql.model;

/**
 * Created by xudi on 17-5-2.
 */
public class CacheMessage {
    private int id;
    private int sender;
    private int target;
    private int syncNo;
    private byte[] message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getSyncNo() {
        return syncNo;
    }

    public void setSyncNo(int syncNo) {
        this.syncNo = syncNo;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }
}
