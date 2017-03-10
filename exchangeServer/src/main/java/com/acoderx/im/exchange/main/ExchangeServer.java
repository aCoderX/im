package com.acoderx.im.exchange.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xiaobaibai on 2016/12/28.
 */
public class ExchangeServer {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("exchange-application.xml");
    }
}
