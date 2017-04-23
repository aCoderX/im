package com.acoderx.im.du.logic.chat;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.CO_MQ_Sender;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.CMDType;
import com.acoderx.im.entity.Constants;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;

/**
 * Created by xudi on 2017/2/20.
 */
public class CH_CMD_Text extends MessageDeal {
    private CO_MQ_Sender sender = CO_MQ_Sender.getInstance();

    public DataPacketInner deal(DataPacketInner req) throws Exception {
        DataPacket dp = req.getMessage();
        if(CMDType.NOTICE.equals(dp.getCmdType())){
            //点对点消息
            //创建数据包，发给exchange转发
            //DataPacketInner dpisend = new DataPacketInner();
            sender.send(req,"DEALUNIT.TO.EXCHANGE");
        }
        DataPacket dpAck = new DataPacket(CMDType.ACK,dp.getCMD(), Constants.SERVER ,dp.getOriginId(),dp.getRandomNum(),dp.getMsgTime(),Constants.SUCCESS);
        DataPacketInner dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);
        return dpiAck;
    }
}
