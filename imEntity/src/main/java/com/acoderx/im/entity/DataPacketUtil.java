package com.acoderx.im.entity;

/**
 * Created by xiaobaibai on 2017/2/9.
 */
public class DataPacketUtil {
    private static DataPacketTransform transform=null;

    public static DataPacketTransform getDataPacketTransform(){
        if(transform==null){
            synchronized (DataPacket.class){
                if(transform==null){
                    transform = new DataPacketTransform();
                }
            }
        }
        return transform;
    }
}
