package com.acoderx.im.zookeeper.im.websocket.rabbitmq;

import com.acoderx.im.websocket.rabbitmq.MQSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xiaobaibai on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:webSocket-application.xml")
public class MQSenderTest {
    @Autowired
    MQSender ms;
    @Test
    public void send() throws Exception {
       // ms.send("哈哈哈222");
    }

}