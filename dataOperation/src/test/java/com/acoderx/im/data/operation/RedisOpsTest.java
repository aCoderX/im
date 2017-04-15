package com.acoderx.im.data.operation;

import com.acoderx.im.redis.RedisKeyUserInfo;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by xudi on 2017/2/12.
 */
public class RedisOpsTest {
    @Test
    public void opsForHash() throws Exception {
        RedisOps redisOps = new RedisOps();

        redisOps.opsForSet().add(new RedisKeyUserInfo.UserSessions("222"),"ssss","111","222");
        Set<String> s = redisOps.opsForSet().members(new RedisKeyUserInfo.UserSessions("1"));
        for (String res : s){
            System.out.println(res);
        }
    }

}