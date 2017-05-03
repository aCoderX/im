package com.acoderx.im.du.logic.message;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.CMDType;
import com.acoderx.im.entity.Constants;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.utils.StringUtils;

/**
 * Created by xudi on 17-5-2.
 */
public class MG_CMD_Fin extends MessageDeal {

    @Override
    public DataPacketInner deal(DataPacketInner req) throws Exception {
        DataPacket dp = req.getMessage();
        String targetID = dp.getTargetId();
        String[] result = StringUtils.stringToSubfields(dp.getSubField());
        int syncNo = Integer.valueOf(result[0]);
        MySQLOperations.getInstance().getCacheMessageDao().finBeforeCacheMessage(Integer.valueOf(targetID),syncNo);
        DataPacket dpAck = new DataPacket(CMDType.ACK,dp.getCMD(), Constants.SERVER ,dp.getOriginId(),dp.getRandomNum(),dp.getMsgTime(),Constants.SUCCESS);
        DataPacketInner dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);
        return dpiAck;
    }
}
