package com.acoderx.im.data.redis.interf;

import com.acoderx.im.redis.RedisKey;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by xiaobaibai on 2017/3/5.
 */
public interface SetOperation {
    Set<String> difference(RedisKey key, RedisKey otherKey);

    Set<String> difference(RedisKey key, Collection<RedisKey> otherKeys);

    Long differenceAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey);

    Long differenceAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey);

    Set<String> intersect(RedisKey key, RedisKey otherKey);

    Set<String> intersect(RedisKey key, Collection<RedisKey> otherKeys);

    Long intersectAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey);

    Long intersectAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey);

    Set<String> union(RedisKey key, RedisKey otherKey);

    Set<String> union(RedisKey key, Collection<RedisKey> otherKeys);

    Long unionAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey);

    Long unionAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey);

    Long add(RedisKey key, String... values);

    Boolean isMember(RedisKey key, String o);

    Set<String> members(RedisKey key);

    Boolean move(RedisKey key, String value, RedisKey destKey);

    String randomMember(RedisKey key);

    Set<String> distinctRandomMembers(RedisKey key, long count);

    List<String> randomMembers(RedisKey key, long count);

    Long remove(RedisKey key, String... values);

    String pop(RedisKey key);

    Long size(RedisKey key);
}
