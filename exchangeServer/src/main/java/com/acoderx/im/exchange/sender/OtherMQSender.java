package com.acoderx.im.exchange.sender;

import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.DataPacketUtil;
import com.acoderx.im.entity.LoggerConf;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by xudi on 17-4-3.
 */

@Component
public class OtherMQSender {

    private final static String QUEUE_NAME = "MSG_FROM_OTHEREXCHANGE_TO_EXCHANGE";
    private final static String ROUTINGKEY = "EXCHANGE.TO.EXCHANGE";
    private final static String EXCHANGE_NAME = "Instant_Message";

    private Map<String,ConnectionFactory> connectionFactoryMap = new HashMap<>();

    Logger logger = LoggerConf.getLogger(OtherMQSender.class);
    private static OtherMQSender sender = new OtherMQSender();
    private OtherMQSender(){}
    public static OtherMQSender getInstance(){
        return sender;
    }


    private Connection getConnection(String host) throws IOException, TimeoutException {

        if(connectionFactoryMap.get(host)==null){
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setUsername("admin");
            factory.setPassword("admin");
            connectionFactoryMap.put(host,factory);
            Connection connection = factory.newConnection();
            return connection;
        }else {
            return connectionFactoryMap.get(host).newConnection();
        }
    }

    public void send(String host,DataPacketInner dpi) throws IOException, TimeoutException {
        logger.info("EXCHANGE:"+dpi+"发往"+host);

        Connection connection = getConnection(host);
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",false,false,null);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTINGKEY);

        channel.basicPublish("", QUEUE_NAME, null, DataPacketUtil.getDataPacketTransform().innerObjectToByte(dpi));
        channel.close();
        connection.close();
    }
}

