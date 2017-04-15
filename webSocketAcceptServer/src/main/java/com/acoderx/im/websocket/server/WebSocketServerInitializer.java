package com.acoderx.im.websocket.server;

import com.acoderx.im.websocket.rabbitmq.MQSender;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author xudi
 * @date 2016/12/26
 * 
*/
public class WebSocketServerInitializer extends ChannelInitializer {
    private MQSender mqSender;
    public WebSocketServerInitializer(MQSender mqSender) {
        this.mqSender = mqSender;
    }
    public WebSocketServerInitializer(){
        super();
    };

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(65536));
        p.addLast(new WebSocketServerHandle(mqSender));

    }
}
