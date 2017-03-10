package com.acoderx.im.entity;

import java.io.Serializable;

/**
 * Created by xiaobaibai on 2017/2/8.
 */
public class DataPacket implements Serializable {
    private String cmdType;//命令种类
    private String CMD;//命令标识
    private String originId;//目标id
    private String targetId;//源id
    private int randomNum = 0;//包的随机数（客户端定）
    private int msgTime = 0;//时间戳
    private String subField = null;//包内容

    public DataPacket(){

    }
    public DataPacket(String message) {
        String data[] = message.split("\t");
        this.cmdType=data[0];
        this.CMD=data[1];
        this.originId=data[2];
        this.targetId=data[3];
        this.randomNum=Integer.parseInt(data[4]);
        this.msgTime=Integer.parseInt(data[5]);

        StringBuilder sb = new StringBuilder();
        for (int i = 6; i < data.length; i++) {
            sb.append(data[i]);
            sb.append('\t');
        }
        this.subField = sb.toString();
    }

    public DataPacket(String cmdType,String CMD, String originId, String targetId, int randomNum, int msgTime, String subField) {
        this.cmdType = cmdType;
        this.CMD = CMD;
        this.originId = originId;
        this.targetId = targetId;
        this.randomNum = randomNum;
        this.msgTime = msgTime;
        this.subField = subField;
    }

    public String toMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.cmdType);
        sb.append("\t");
        sb.append(this.CMD);
        sb.append("\t");
        sb.append(this.originId);
        sb.append("\t");
        sb.append(this.targetId);
        sb.append("\t");
        sb.append(this.randomNum);
        sb.append("\t");
        sb.append(this.msgTime);
        sb.append("\t");
        sb.append(this.getSubField());
        return sb.toString();
    }

    public String getCMD() {
        return CMD;
    }

    public void setCMD(String CMD) {
        this.CMD = CMD;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getSubField() {
        return subField;
    }

    public void setSubField(String subField) {
        this.subField = subField;
    }

    public int getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(int msgTime) {
        this.msgTime = msgTime;
    }

    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    @Override
    public String toString() {
        return "DataPacket{" +
                "cmdType='" + cmdType + '\'' +
                ", CMD='" + CMD + '\'' +
                ", originId='" + originId + '\'' +
                ", targetId='" + targetId + '\'' +
                ", randomNum=" + randomNum +
                ", msgTime=" + msgTime +
                ", subField='" + subField + '\'' +
                '}';
    }
}
