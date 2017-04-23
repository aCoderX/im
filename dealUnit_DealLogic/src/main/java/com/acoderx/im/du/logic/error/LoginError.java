package com.acoderx.im.du.logic.error;

import com.acoderx.im.entity.*;
import com.acoderx.im.utils.StringUtils;

/**
 * Created by xudi on 17-4-22.
 */
public class LoginError {
    private static final String WRONG_ACCONT_OR_PASS= "账号或密码不正确";
    public static DataPacketInner wrongAccontOrPass(DataPacketInner dpi){
        DataPacketInner dpiAck;
        DataPacket dp = dpi.getMessage();

        DataPacket dpAck = new DataPacket(CMDType.ACK,CMD.CMD_LOGIN, Constants.SERVER,dp.getOriginId(),dp.getRandomNum(),dp.getMsgTime(), StringUtils.subFieldsTostring(Constants.FAIL,WRONG_ACCONT_OR_PASS));
        dpiAck = new DataPacketInner(dpi.getSessionID(),dpi.getTargetId(),dpAck);

        return dpiAck;
    }
}
