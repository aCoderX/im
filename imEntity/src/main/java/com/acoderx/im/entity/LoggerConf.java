package com.acoderx.im.entity;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

/**
 * Created by xiaobaibai on 2017/3/9.
 */
public class LoggerConf {
    public static Logger getLogger(Class clazz){
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/conf/log4j.properties");
        return Logger.getLogger(clazz);
    }

    public static void main(String[] args) throws IOException {
        getLogger(LoggerConf.class).info("sssssssss");
    }
}
