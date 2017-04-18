package com.acoderx.im.websocket.server;

import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.LoggerConf;
import com.acoderx.im.entity.SessionProperty;
import com.acoderx.im.websocket.rabbitmq.MQSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author xudi
 * @date 2016/12/26
 * 
*/
@Component
public class WebSocketServerHandle extends SimpleChannelInboundHandler {
    private WebSocketServerHandshaker handshaker;
    private Logger logger = LoggerConf.getLogger(WebSocketServerHandle.class);

    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static final DualHashBidiMap<String,ChannelId> UserChannels = new DualHashBidiMap();
    public static final DualHashBidiMap<String,ChannelId> tempChannels = new DualHashBidiMap();

    @Resource
    private MQSender mqSender;

    public WebSocketServerHandle(MQSender mqSender){
        this.mqSender=mqSender;
    }
    public WebSocketServerHandle(){
        super();
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
        }else if(msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("WEBSOCKET:"+"create connect");
        channels.add(ctx.channel());
        super.channelActive(ctx);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        //断开连接，发送下线指令
        ChannelId channelId = ctx.channel().id();
        String sessionId = UserChannels.getKey(channelId);
        if(sessionId!=null){
            logger.info("WEBSOCKET:"+"session:"+sessionId+"断开连接");
            //下线包
            DataPacket dp = new DataPacket("REQ","CMD_LOGOUT","00001","00001",0,(int) (System.currentTimeMillis() / 1000),"Z");
            DataPacketInner dpi = new DataPacketInner(sessionId,"00001",dp);
            mqSender.send(dpi);
            UserChannels.removeValue(ctx.channel().id());
        }
        sessionId = tempChannels.getKey(channelId);
        if(sessionId!=null){
            logger.info("WEBSOCKET:"+"session:"+sessionId+"断注册开连接");
            tempChannels.removeValue(ctx.channel().id());
        }
    }


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // Handle a bad request.
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.MULTI_STATUS.BAD_REQUEST));
            return;
        }

        // Allow only GET methods.
        if (req.method() != HttpMethod.GET) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.FORBIDDEN));
            return;
        }

        // Handshake
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req),
                null, true);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }
    private static String getWebSocketLocation(FullHttpRequest req) {
        String location = req.headers().get(HttpHeaderNames.HOST)+"";
        return "ws://" + location;
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

        // Check for closing frame
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }

        String request = ((TextWebSocketFrame) frame).text();
        DataPacket dp = new DataPacket(request);
        logger.info("WEBSOCKET:"+dp.toString());
        int nowTime = (int) (System.currentTimeMillis() / 1000);
        dp.setMsgTime(nowTime);
        SessionProperty sessionProperty = new SessionProperty(WebSocketServer.HOST,WebSocketServer.PORT,ctx.channel().id().asShortText());

        if("CMD_LOGIN".equals(dp.getCMD())){
            //如果是登录，加入channel
            UserChannels.put(sessionProperty.toString(),ctx.channel().id());
            logger.info("WEBSOCKET:"+"session:"+sessionProperty.toString()+"登录，"+WebSocketServer.HOST+"在线人数"+UserChannels.size());
        }else if("CMD_REGISTER".equals(dp.getCMD())){
            tempChannels.put(sessionProperty.toString(),ctx.channel().id());
            logger.info("WEBSOCKET:"+"session:"+sessionProperty.toString()+"注册");
        }

        DataPacketInner dpi = new DataPacketInner(sessionProperty.toString(),"000001",dp);

        logger.info("WEBSOCKET:"+dp.getOriginId()+"发送给用户"+dp.getTargetId()+dp.getSubField());

        mqSender.send(dpi);

    }
}
