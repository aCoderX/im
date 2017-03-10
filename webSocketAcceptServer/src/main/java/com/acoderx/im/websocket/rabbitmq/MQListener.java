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
    private static Logger logger = LoggerConf.getLogger(MQListener.class);


    public void onMessage(Message message) {

        String msg = null;
        try {
            msg = new String(message.getBody(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getBody());

        //String data[] = msg.split("\t");
        //String s[] = data[3].split("\\|");
        /*if(s.length<3){
            logger.error("数据错误"+s.length);
            return;
        }*/
        //logger.info("用户"+data[2]+"接受到"+data[1]+"发的消息"+msg);
        //System.out.println("用户"+data[2]+"接受到"+data[1]+"发的消息"+msg);
        ChannelId channelId = WebSocketServerHandle.UserChannels.get(dpi.getSessionID());
        if(channelId!=null){
            //logger.info("发送给用户"+data[2]);
            System.out.println("发送给用户"+dpi.getMessage().getTargetId());
            io.netty.channel.Channel c = WebSocketServerHandle.channels.find(channelId);
            c.writeAndFlush(new TextWebSocketFrame(dpi.getMessage().toMessage()));
        }

    }
}
