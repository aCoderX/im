package com.acoderx.im.du.logic.message;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.data.mysql.model.CacheMessage;
import com.acoderx.im.du.logic.common.CO_MQ_Sender;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.*;
import com.acoderx.im.utils.StringUtils;

import java.util.List;

/**
 * Created by xudi on 17-5-2.
 */
public class MG_CMD_Sync extends MessageDeal {
    private CO_MQ_Sender sender = CO_MQ_Sender.getInstance();
    @Override
    public DataPacketInner deal(DataPacketInner req) throws Exception {
        DataPacket dp = req.getMessage();
        String senderID = dp.getOriginId();
        String[] result = StringUtils.stringToSubfields(dp.getSubField());
        int syncNo = Integer.valueOf(result[0]);
        List<CacheMessage> list = MySQLOperations.getInstance().getCacheMessageDao().syncCacheMessage(Integer.valueOf(senderID),syncNo);
        for (CacheMessage message:list){
            DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getMessage());
            sender.send(dpi,"DEALUNIT.TO.EXCHANGE");
        }
        DataPacket dpAck = new DataPacket(CMDType.ACK,dp.getCMD(), Constants.SERVER ,dp.getOriginId(),dp.getRandomNum(),dp.getMsgTime(),Constants.SUCCESS);
        DataPacketInner dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);
        return dpiAck;
    }
}
