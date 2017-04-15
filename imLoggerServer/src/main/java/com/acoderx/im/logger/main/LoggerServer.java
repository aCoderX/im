package com.acoderx.im.logger.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudi on 17-4-15.
 */
public class LoggerServer {
    public static void main(String args[]){
        new ClassPathXmlApplicationContext("logger-application.xml");
    }
}
