package com.acoderx.im.du.logic.chat;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.CO_MQ_Sender;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;

/**
 * Created by xiaobaibai on 2017/2/20.
 */
public class CH_CMD_Text extends MessageDeal {
    private CO_MQ_Sender sender = CO_MQ_Sender.getInstance();

    public DataPacketInner deal(DataPacketInner req) throws Exception {
        DataPacket dp = req.getMessage();
        if("NOTICE".equals(dp.getCmdType())){
            //点对点消息
            //创建数据包，发给exchange转发
            //DataPacketInner dpisend = new DataPacketInner();
            sender.send(req,"DEALUNIT.TO.EXCHANGE");
        }
        DataPacket dpAck = new DataPacket("ACK",dp.getCMD(),"00001",dp.getOriginId(),dp.getRandomNum(),dp.getMsgTime(),"S");
        DataPacketInner dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);
        return dpiAck;
    }
}
