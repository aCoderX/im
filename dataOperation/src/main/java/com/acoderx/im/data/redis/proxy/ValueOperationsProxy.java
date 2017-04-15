package com.acoderx.im.data.redis.proxy;

import com.acoderx.im.data.redis.interf.ValueOperation;
import com.acoderx.im.redis.RedisKey;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudi on 2017/3/5.
 */
public class ValueOperationsProxy implements ValueOperation {
    private ValueOperations<String,String> valueOperations;
    public ValueOperationsProxy(ValueOperations valueOperations){
        this.valueOperations = valueOperations;
    }
    public void set(RedisKey key, String value) {
        valueOperations.set(key.keyName(),value);
    }

    public void set(RedisKey key, String value, long timeout, TimeUnit unit) {

    }

    public Boolean setIfAbsent(RedisKey key, String value) {
        return null;
    }

    public void multiSet(Map<RedisKey, String> m) {

    }

    public Boolean multiSetIfAbsent(Map<RedisKey, String> m) {
        return null;
    }

    public String get(RedisKey key) {
        return valueOperations.get(key.keyName());
    }

    public String get(String string) {
        return valueOperations.get(string);
    }

    public String getAndSet(RedisKey key, String value) {
        return null;
    }

    public List<String> multiGet(Collection<RedisKey> keys) {
        return null;
    }

    public Long increment(RedisKey key, long delta) {
        return null;
    }

    public Double increment(RedisKey key, double delta) {
        return null;
    }

    public Integer append(RedisKey key, String value) {
        return null;
    }

    public String get(RedisKey key, long start, long end) {
        return null;
    }

    public void set(RedisKey key, String value, long offset) {

    }

    public Long size(RedisKey key) {
        return null;
    }
}
