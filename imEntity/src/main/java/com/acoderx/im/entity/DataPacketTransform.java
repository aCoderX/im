package com.acoderx.im.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by xiaobaibai on 2017/2/9.
 */
public class DataPacketTransform {
    public DataPacketInner byteToInnerObject(byte[] bytes){
        DataPacketInner dpi = new DataPacketInner();
        try {
            ObjectInputStream out = new ObjectInputStream(new ByteArrayInputStream(bytes));
            dpi = (DataPacketInner) out.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dpi;
    }
}
