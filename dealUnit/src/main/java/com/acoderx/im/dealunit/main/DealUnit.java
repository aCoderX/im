package com.acoderx.im.dealunit.main;

import com.acoderx.im.entity.LoggerConf;
import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudi on 2016/12/28.
 */
public class DealUnit {
    private static Logger logger = LoggerConf.getLogger(DealUnit.class);
    public static final String HOST = System.getProperty("HOST");

    public static void main(String[] args) {
        if(HOST==null){
            logger.error("DEALUNIT:"+"HOST不能为空");
            return;
        }
        new ClassPathXmlApplicationContext("dealUnit-application.xml");
    }
}
