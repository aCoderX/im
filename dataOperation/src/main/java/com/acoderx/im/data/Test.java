package com.acoderx.im.data;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.data.mysql.model.HashValue;
import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.redis.RedisKeyUserInfo;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by xiaobaibai on 2017/2/5.
 */
public class Test {
    public static void main(String[] args) {
        HashValue hashValue = new HashValue();
        hashValue.setTableName("HASHS_KEY_1");
        hashValue.setKey("USER:1:INFO");
        //System.out.println(MySQLOperations.getInstance().getHashValueDao().test(hashValue).get(0).getValue());
        RedisOps redisOps = new RedisOps();
        System.out.println(redisOps.opsForHash().get(new RedisKeyUserInfo.UserInfo("1"),"username"));
    }
}
