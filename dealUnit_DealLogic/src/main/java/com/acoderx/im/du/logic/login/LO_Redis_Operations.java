package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.data.redis.interf.HashOperation;
import com.acoderx.im.redis.RedisKeyUserInfo;

/**
 * Created by xudi on 2017/2/12.
 */
public class LO_Redis_Operations {
    private RedisOps redisOps;
    public LO_Redis_Operations(RedisOps redisOps){
        this.redisOps = redisOps;
    }
    public String getUserIdByAccount(String account) {
        HashOperation hashOps = redisOps.opsForHash();
        return hashOps.get(new RedisKeyUserInfo.UserAccountID(account), account);
    }
}
