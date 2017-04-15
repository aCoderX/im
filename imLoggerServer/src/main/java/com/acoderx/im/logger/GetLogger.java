package com.acoderx.im.logger;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudi on 17-4-15.
 */
public class GetLogger {
    public static Logger getLogger(Class clazz){
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/conf/log4j-logServer.properties");
        return LoggerFactory.getLogger(clazz);
    }
}
