package com.acoderx.im.exchange.listener;

import com.acoderx.im.exchange.sender.MQSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by xudi on 2016/12/28.
 */
@Component
public class MQWebSocketListener implements MessageListener{
    @Autowired
    private MQSender mqSender;
    public void onMessage(Message message) {
            //发往数据处理模块
            mqSender.send(message,"EXCHANGE.TO.DEALUNIT");
    }
}
