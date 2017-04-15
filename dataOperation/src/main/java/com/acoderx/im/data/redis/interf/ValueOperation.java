package com.acoderx.im.data.redis.interf;

import com.acoderx.im.redis.RedisKey;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudi on 2017/3/5.
 */
public interface ValueOperation {
    void set(RedisKey key, String value);

    /**
     * Set {@code key} to hold the string {@code value} until {@code timeout}.
     *
     * @param key
     * @param value
     * @param timeout
     * @param units
     * @see http://redis.io/commands/set
     */
    void set(RedisKey key, String value, long timeout, TimeUnit unit);

    Boolean setIfAbsent(RedisKey key, String value);

    void multiSet(Map<RedisKey, String> m);

    Boolean multiSetIfAbsent(Map<RedisKey, String> m);

    String get(RedisKey key);
    String get(String string);

    String getAndSet(RedisKey key, String value);

    List<String> multiGet(Collection<RedisKey> keys);

    Long increment(RedisKey key, long delta);

    Double increment(RedisKey key, double delta);

    Integer append(RedisKey key, String value);

    String get(RedisKey key, long start, long end);

    void set(RedisKey key, String value, long offset);

    Long size(RedisKey key);
}
