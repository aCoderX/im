package com.acoderx.im.data.redis.proxy;

import com.acoderx.im.data.redis.interf.ZSetOperation;
import com.acoderx.im.redis.RedisKey;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collection;
import java.util.Set;

/**
 * Created by xudi on 17-4-29.
 */
public class ZSetOperationProx implements ZSetOperation {
    private ZSetOperations<String,String> zsetOperations;
    public ZSetOperationProx(ZSetOperations<String,String> zsetOperations){
        this.zsetOperations = zsetOperations;
    }
    @Override
    public Long intersectAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey) {
        return null;
    }

    @Override
    public Long intersectAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey) {
        return null;
    }

    @Override
    public Long unionAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey) {
        return null;
    }

    @Override
    public Long unionAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey) {
        return null;
    }

    @Override
    public Set<String> range(RedisKey key, long start, long end) {
        return null;
    }

    @Override
    public Set<String> reverseRange(RedisKey key, long start, long end) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScores(RedisKey key, long start, long end) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(RedisKey key, long start, long end) {
        return null;
    }

    @Override
    public Set<String> rangeByScore(RedisKey key, double min, double max) {
        return null;
    }

    @Override
    public Set<String> rangeByScore(RedisKey key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Set<String> reverseRangeByScore(RedisKey key, double min, double max) {
        return null;
    }

    @Override
    public Set<String> reverseRangeByScore(RedisKey key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(RedisKey key, double min, double max) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(RedisKey key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(RedisKey key, double min, double max) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(RedisKey key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Boolean add(RedisKey key, String value, double score) {
        return null;
    }

    @Override
    public Long add(RedisKey key, Set<ZSetOperations.TypedTuple<String>> tuples) {
        return null;
    }

    @Override
    public Double incrementScore(RedisKey key, String value, double delta) {
        return null;
    }

    @Override
    public Long rank(RedisKey key, String o) {
        return null;
    }

    @Override
    public Long reverseRank(RedisKey key, String o) {
        return null;
    }

    @Override
    public Double score(RedisKey key, String o) {
        return null;
    }

    @Override
    public Long remove(RedisKey key, String... values) {
        return null;
    }

    @Override
    public Long removeRange(RedisKey key, long start, long end) {
        return null;
    }

    @Override
    public Long removeRangeByScore(RedisKey key, double min, double max) {
        return null;
    }

    @Override
    public Long count(RedisKey key, double min, double max) {
        return null;
    }
}
