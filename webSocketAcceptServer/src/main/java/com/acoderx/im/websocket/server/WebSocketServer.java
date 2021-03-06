package com.acoderx.im.websocket.server;

import com.acoderx.im.entity.LoggerConf;
import com.acoderx.im.websocket.rabbitmq.MQSender;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @author xudi
 * @date 2016/12/26
 * 
*/
public class WebSocketServer {
    public static final String HOST = System.getProperty("HOST");
    public static final int PORT = Integer.parseInt(System.getProperty("PORT"));
    static final boolean SSL = System.getProperty("SSL") != null;


    /** 用于分配处理业务线程的线程组个数 */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime()
            .availableProcessors() * 2; // 默认
    /** 业务出现线程大小 */
    protected static final int BIZTHREADSIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
    private static Logger logger = LoggerConf.getLogger(WebSocketServer.class);
    public static void main(String[] args) throws CertificateException, SSLException {
        if(HOST==null){
            logger.error("WEBSOCKET:"+"HOST不能为空");
            return;
        }
        if(PORT==0){
            logger.error("WEBSOCKET:"+"PORT不能为空");
            return;
        }
        final SslContext sslCtx;
        if (SSL) {
            logger.info("启动ssl");
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("webSocket-application.xml");
        MQSender mqSender = (MQSender) applicationContext.getBean("MQSender");
        ServerBootstrap bootStrap = new ServerBootstrap();
        bootStrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketServerInitializer(sslCtx,mqSender));
        try {
            logger.info("WEBSOCKET:"+"bind"+HOST+":"+PORT);
            ChannelFuture future = bootStrap.bind(HOST, PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
