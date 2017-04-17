package com.acoderx.im.data.redis.proxy;

import com.acoderx.im.data.redis.interf.KeyOperation;
import com.acoderx.im.redis.RedisKey;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudi on 2017/3/5.
 */
public class KeyOperationsProxy implements KeyOperation {
    private RedisTemplate<String,String> redisTemplate;
    public KeyOperationsProxy(RedisTemplate<String,String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void delete(String key) {

    }

    public void delete(RedisKey key) {
        redisTemplate.delete(key.keyName());
    }

    public void delete(Collection<RedisKey> keys) {

    }

    public Boolean hasKey(RedisKey key) {
        return redisTemplate.hasKey(key.keyName());
    }

    public Boolean expire(RedisKey key, long timeout, TimeUnit unit) {
        return null;
    }

    public Boolean expireAt(RedisKey key, Date date) {
        return null;
    }

    public Long getExpire(RedisKey key) {
        return null;
    }

    public Long getExpire(RedisKey key, TimeUnit timeUnit) {
        return null;
    }

    public Set<String> keys(String pattern) {
        return null;
    }

    public Boolean persist(RedisKey key) {
        return null;
    }

    public DataType type(RedisKey key) {
        return null;
    }
}
