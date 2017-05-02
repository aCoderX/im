package com.acoderx.im.websocket.server;

import com.acoderx.im.websocket.rabbitmq.MQSender;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @author xudi
 * @date 2016/12/26
 * 
*/
public class WebSocketServerInitializer extends ChannelInitializer {
    private MQSender mqSender;
    private final SslContext sslCtx;
    public WebSocketServerInitializer(SslContext sslContext, MQSender mqSender) {
        this.sslCtx = sslContext;
        this.mqSender = mqSender;
    }

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            SSLEngine sslEngine = sslCtx.newEngine(ch.alloc());
            sslEngine.setUseClientMode(false);
            sslEngine.setNeedClientAuth(false);
            p.addLast(new SslHandler(sslEngine));
        }
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(65536));
        p.addLast(new WebSocketServerHandle(mqSender));

    }
}
