package com.acoderx.im.entity;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by xudi on 2017/3/9.
 */
public class LoggerConf {
    public static final boolean SINGLE = true;
    public static Logger getLogger(Class clazz){
        if(SINGLE){
            //单台机器
            PropertyConfigurator.configure(System.getProperty("user.dir")+"/conf/log4j.properties");
            return LoggerFactory.getLogger(clazz);
        }else{
            //多机环境
            return MQLogger.getInstance();
        }
    }

    public static void main(String[] args) throws IOException {
        getLogger(LoggerConf.class).debug("sssssssss");
        getLogger(LoggerConf.class).debug("sssssssss1");
        getLogger(LoggerConf.class).debug("sssssssss2");
        getLogger(LoggerConf.class).debug("sssssssss3");
    }
}
