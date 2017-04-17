package com.acoderx.im.data.callback;

import com.acoderx.im.data.mysql.dao.HashValueDao;
import com.acoderx.im.data.mysql.dao.SetValueDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudi on 2017/2/14.
 */
public class MySQLOperations {
    private static MySQLOperations mysqlOperation = new MySQLOperations();

    private HashValueDao hashValueDao;
    private SetValueDao setValueDao;

    private MySQLOperations(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dataoperation_mybatis_beans.xml");
        hashValueDao = (HashValueDao) context.getBean("hashValueDao");
        setValueDao = (SetValueDao) context.getBean("setValueDao");
    }

    public static MySQLOperations getInstance(){
        return mysqlOperation;
    }

    public HashValueDao getHashValueDao(){
        return hashValueDao;
    }
    public SetValueDao getSetValueDao(){
        return setValueDao;
    }
}
