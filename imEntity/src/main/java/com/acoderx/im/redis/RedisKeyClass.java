package com.acoderx.im.redis;

/**
 * Created by xiaobaibai on 2017/2/12.
 */
public class RedisKeyClass implements RedisKey{
    protected String name;
    protected boolean sync;
    protected String type;
    public String keyName() {
        return name;
    }

    public boolean syncFlag() {
        return sync;
    }

    public String keyType() {
        return type;
    }
}
