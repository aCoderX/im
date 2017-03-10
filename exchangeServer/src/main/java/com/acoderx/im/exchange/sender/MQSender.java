package com.acoderx.im.exchange.sender;

import com.acoderx.im.entity.DataPacketInner;
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

    public void send(Message msg, String key){
        amqpTemplate.convertAndSend("Instant_Message",key,msg);
    }
    public void send(DataPacketInner dpi, String key){
        amqpTemplate.convertAndSend("Instant_Message",key,dpi);
    }
}
