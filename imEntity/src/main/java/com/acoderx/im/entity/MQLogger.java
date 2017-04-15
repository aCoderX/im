package com.acoderx.im.entity;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by xudi on 17-4-15.
 */
public class MQLogger extends AbstractMQLogger {
    private static RabbitTemplate rabbitTemplate;
    private static MQLogger instance;
    private MQLogger(){}
    public static MQLogger getInstance(){
        if(instance==null){
            synchronized (MQLogger.class){
                if(instance==null){
                    rabbitTemplate = (RabbitTemplate) new ClassPathXmlApplicationContext("entity-application.xml").getBean("rabbitTemplate");
                    instance = new MQLogger();
                }
            }
        }
        return instance;
    }

    @Override
    public void debug(String msg) {
        rabbitTemplate.convertAndSend(Constants.MQ_EXCHANGE_NAME,Constants.MQ_KEY_LOGGER_DEBUG,filterMsg(msg));
    }

    @Override
    public void info(String msg) {
        rabbitTemplate.convertAndSend(Constants.MQ_EXCHANGE_NAME,Constants.MQ_KEY_LOGGER_INFO,filterMsg(msg));
    }

    @Override
    public void warn(String msg) {
        rabbitTemplate.convertAndSend(Constants.MQ_EXCHANGE_NAME,Constants.MQ_KEY_LOGGER_WARN,filterMsg(msg));
    }

    @Override
    public void error(String msg) {
        rabbitTemplate.convertAndSend(Constants.MQ_EXCHANGE_NAME,Constants.MQ_KEY_LOGGER_ERROR,filterMsg(msg));
    }

    private String filterMsg(String msg){
        return System.getProperty("HOST")+":"+msg;
    }
}
