package com.acoderx.im.exchange.listener;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.DataPacketUtil;
import com.acoderx.im.exchange.sender.MQSender;
import com.acoderx.im.redis.RedisKeyUserInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * Created by xiaobaibai on 2016/12/28.
 */
@Component
public class MQDealUnitListener implements MessageListener{
    @Autowired
    private MQSender mqSender;
    public void onMessage(Message message) {

        DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getBody());
        DataPacket dp = dpi.getMessage();

        if(dpi.getSessionID()==null){
            return;
        }
        switch (dp.getCmdType()){
            case "ACK" :
                mqSender.send(dpi,"EXCHANGE.TO.WEBSOCKET");
                break;
            case "NOTICE":
                dealNoticeMessage(dpi);
                break;
        }
    }

    private void dealNoticeMessage(DataPacketInner dpi){
        RedisOps redisOps = new RedisOps();
        DataPacket dp = dpi.getMessage();
        String targetId = dp.getTargetId();
        Set<String> targetSessions = redisOps.opsForSet().members(new RedisKeyUserInfo.UserSessions(targetId));
        if(targetSessions==null||targetSessions.size()<=0){
            return;
        }
        for(String targetSession : targetSessions){
            DataPacketInner dpiSend = new DataPacketInner(targetSession, dpi.getTargetId(), dp);
            //TDDO 根据session判断是否是本机，现在默认是本机
            mqSender.send(dpiSend,"EXCHANGE.TO.WEBSOCKET");
        }
    }
}
