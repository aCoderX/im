package com.acoderx.im.data;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.data.mysql.model.HashValue;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by xiaobaibai on 2017/2/5.
 */
public class Test {
    public static void main(String[] args) {
        HashValue hashValue = new HashValue();
        hashValue.setTableName("testkeyvaule");
        hashValue.setKey("USER:1:INFO");
        System.out.println(MySQLOperations.getInstance().getHashValueDao().test(hashValue).getValue());
    }
}
