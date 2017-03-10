package com.acoderx.im.entity;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;

/**
 * Created by xiaobaibai on 2017/3/9.
 */
public class LoggerConf {
    public static Logger getLogger(Class clazz) throws FileNotFoundException {
        PropertyConfigurator.configure(new FileInputStream(new File("D:/log4j.properties")));
        return Logger.getLogger(clazz);
    }

    public static void main(String[] args) throws IOException {
        getLogger(LoggerConf.class).info("ssss");
    }
}
