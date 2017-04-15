package com.acoderx.im.data.callback;

import com.acoderx.im.data.mysql.dao.HashValueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudi on 2017/2/14.
 */
public class MySQLOperations {
    private static MySQLOperations mysqlOperation = new MySQLOperations();

    @Autowired
    private HashValueDao hashValueDao;

    private MySQLOperations(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dataoperation_mybatis_beans.xml");
        hashValueDao = (HashValueDao) context.getBean("hashValueDao");
    }

    public static MySQLOperations getInstance(){
        return mysqlOperation;
    }

    public HashValueDao getHashValueDao(){
        return hashValueDao;
    }
}
