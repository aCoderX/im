package com.acoderx.im.logger.listener;

import com.acoderx.im.logger.GetLogger;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by xudi on 17-4-15.
 */
public class ErrorLoggerListen implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        Logger logger = GetLogger.getLogger(ErrorLoggerListen.class);
        logger.error(msg);
    }
}
