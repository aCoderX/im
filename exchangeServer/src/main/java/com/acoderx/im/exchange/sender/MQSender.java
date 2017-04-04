package com.acoderx.im.exchange.sender;

import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.LoggerConf;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiaobaibai on 2016/12/28.
 */
@Component
public class MQSender {
    @Autowired
    private RabbitTemplate amqpTemplate;
    private Logger logger = LoggerConf.getLogger(MQSender.class);

    public void send(Message msg, String key){
        logger.info("EXCHANGE:"+msg+"key:"+key);
        amqpTemplate.convertAndSend("Instant_Message",key,msg);
    }
    public void send(DataPacketInner dpi, String key){
        logger.info("EXCHANGE:"+dpi+"key:"+key);
        amqpTemplate.convertAndSend("Instant_Message",key,dpi);
    }
}
