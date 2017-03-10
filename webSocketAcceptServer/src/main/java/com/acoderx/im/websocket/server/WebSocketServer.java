package com.acoderx.im.websocket.server;

import com.acoderx.im.websocket.rabbitmq.MQSender;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiaobaibai
 * @date 2016/12/26
 * 
*/
public class WebSocketServer {
    /** 用于分配处理业务线程的线程组个数 */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime()
            .availableProcessors() * 2; // 默认
    /** 业务出现线程大小 */
    protected static final int BIZTHREADSIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("webSocket-application.xml");
        MQSender mqSender = (MQSender) applicationContext.getBean("MQSender");
        ServerBootstrap bootStrap = new ServerBootstrap();
        bootStrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketServerInitializer(mqSender));
        try {
            ChannelFuture future = bootStrap.bind("localhost", 9999).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
