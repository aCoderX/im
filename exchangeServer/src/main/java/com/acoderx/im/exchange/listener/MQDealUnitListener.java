package com.acoderx.im.exchange.listener;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.entity.*;
import com.acoderx.im.exchange.main.ExchangeServer;
import com.acoderx.im.exchange.sender.MQSender;
import com.acoderx.im.exchange.sender.OtherMQSender;
import com.acoderx.im.redis.RedisKeyUserInfo;
import org.slf4j.Logger;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Created by xudi on 2016/12/28.
 */
@Component
public class MQDealUnitListener implements MessageListener{
    @Autowired
    private MQSender mqSender;
    @Autowired
    private OtherMQSender otherSender;
    private Logger logger = LoggerConf.getLogger(MQDealUnitListener.class);
    public void onMessage(Message message) {

        DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getBody());
        DataPacket dp = dpi.getMessage();

        if(dpi.getSessionID()==null){
            return;
        }
        switch (dp.getCmdType()){
            case "ACK" :
                //TODO 判断是发往socket还是websocket模块的，需要增加设备字段
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
            logger.error("EXCHANGE："+"接受者不在线");
            return;
        }
        for(String targetSession : targetSessions){
            DataPacketInner dpiSend = new DataPacketInner(targetSession, dpi.getTargetId(), dp);
            String host = new SessionProperty(targetSession).getHost();
            if(ExchangeServer.HOST.equals(host)){
                //本机的session
                mqSender.send(dpiSend,"EXCHANGE.TO.WEBSOCKET");
            }else{
                logger.info("EXCHANGE:"+"接受者不在本机，位于"+host);
                try {
                    otherSender.send(host,dpiSend);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    logger.error("EXCHANGE:"+"连接服务器超时");
                    e.printStackTrace();
                }
            }

        }
    }
}
