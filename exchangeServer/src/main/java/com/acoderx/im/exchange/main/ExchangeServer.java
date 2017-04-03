package com.acoderx.im.exchange.main;

import com.acoderx.im.entity.LoggerConf;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xiaobaibai on 2016/12/28.
 */
public class ExchangeServer {
    private static Logger logger = LoggerConf.getLogger(ExchangeServer.class);
    public static final String HOST = System.getProperty("HOST");

    public static void main(String[] args) {
        if(HOST==null){
            logger.error("EXCHANGE:"+"HOST不能为空");
            return;
        }
        new ClassPathXmlApplicationContext("exchange-application.xml");
    }
}
