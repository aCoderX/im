package com.acoderx.im.exchange.listener;

import com.acoderx.im.exchange.sender.MQSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xudi on 17-4-4.
 */
public class OtherExchangeListener implements MessageListener {

    @Autowired
    private MQSender sender;
    @Override
    public void onMessage(Message message) {
        //TODO 判断是发往socket还是websocket模块的，需要增加设备字段
        sender.send(message,"EXCHANGE.TO.WEBSOCKET");
    }
}
