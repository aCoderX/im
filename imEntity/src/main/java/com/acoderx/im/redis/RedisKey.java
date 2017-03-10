package com.acoderx.im.redis;

/**
 * Created by xiaobaibai on 2017/2/12.
 */
public interface RedisKey {
    public String keyName();
    public boolean syncFlag();
    public String keyType();
}
