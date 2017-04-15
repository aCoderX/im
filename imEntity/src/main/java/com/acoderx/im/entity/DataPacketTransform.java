package com.acoderx.im.entity;

import java.io.*;

/**
 * Created by xudi on 2017/2/9.
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

    public byte[] innerObjectToByte(DataPacketInner dpi){
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bo);
            out.writeObject(dpi);
            bytes = bo.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
