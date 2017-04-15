package com.acoderx.im.data.redis.proxy;

import com.acoderx.im.data.redis.interf.SetOperation;
import com.acoderx.im.redis.RedisKey;
import org.springframework.data.redis.core.SetOperations;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;


/**
 * Created by xudi on 2017/3/5.
 */
public class SetOperationsProxy implements SetOperation {
    private SetOperations<String,String> setOperations;
    public SetOperationsProxy(SetOperations setOperations){
        this.setOperations=setOperations;
    }

    public Set<String> members(RedisKey key) {
        return setOperations.members(key.keyName());
    }

    public Boolean move(RedisKey key, String value, RedisKey destKey) {
        return null;
    }

    public String randomMember(RedisKey key) {
        return null;
    }

    public Set<String> distinctRandomMembers(RedisKey key, long count) {
        return null;
    }

    public List<String> randomMembers(RedisKey key, long count) {
        return null;
    }

    public Long remove(RedisKey key, String... values) {
        return setOperations.remove(key.keyName(),values);
    }

    public String pop(RedisKey key) {
        return null;
    }

    public Long size(RedisKey key) {
        return null;
    }

    public Set<String> difference(RedisKey key, RedisKey otherKey) {
        return null;
    }

    public Set<String> difference(RedisKey key, Collection<RedisKey> otherKeys) {
        return null;
    }

    public Long differenceAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey) {
        return null;
    }

    public Long differenceAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey) {
        return null;
    }

    public Set<String> intersect(RedisKey key, RedisKey otherKey) {
        return null;
    }

    public Set<String> intersect(RedisKey key, Collection<RedisKey> otherKeys) {
        return null;
    }

    public Long intersectAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey) {
        return null;
    }

    public Long intersectAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey) {
        return null;
    }

    public Set<String> union(RedisKey key, RedisKey otherKey) {
        return null;
    }

    public Set<String> union(RedisKey key, Collection<RedisKey> otherKeys) {
        return null;
    }

    public Long unionAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey) {
        return null;
    }

    public Long unionAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey) {
        return null;
    }

    public Long add(RedisKey key, String... values) {
        return setOperations.add(key.keyName(),values);
    }

    public Boolean isMember(RedisKey key, String value) {
        return setOperations.isMember(key.keyName(),value);
    }

}
