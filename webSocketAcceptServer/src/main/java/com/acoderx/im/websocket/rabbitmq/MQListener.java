package com.acoderx.im.websocket.rabbitmq;

import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.DataPacketUtil;
import com.acoderx.im.entity.LoggerConf;
import com.acoderx.im.websocket.server.WebSocketServerHandle;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

import static com.acoderx.im.entity.DataPacketUtil.getDataPacketTransform;

/**
 * Created by xiaobaibai on 2016/12/26.
 */
public class MQListener implements MessageListener {
    private Logger logger = LoggerConf.getLogger(MQListener.class);

    public void onMessage(Message message) {
        String msg = null;
        try {
            msg = new String(message.getBody(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getBody());

        ChannelId channelId = WebSocketServerHandle.UserChannels.get(dpi.getSessionID());
        if(channelId!=null){
            logger.info("WEBSOCKET:"+dpi);
            io.netty.channel.Channel c = WebSocketServerHandle.channels.find(channelId);
            c.writeAndFlush(new TextWebSocketFrame(dpi.getMessage().toMessage()));
        }else{
            logger.error("WEBSOCKET:"+"不存在的接受者");
        }

    }
}
