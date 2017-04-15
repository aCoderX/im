package com.acoderx.im.websocket.rabbitmq;

import com.acoderx.im.entity.DataPacketInner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xudi
 * @date 2016/12/26
 * 
*/
@Service
public class MQSender
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(DataPacketInner data){
        rabbitTemplate.convertAndSend("Instant_Message","WEBSOCKET.TO.EXCHANGE",data);
    }
}
