package com.acoderx.im.data.redis.interf;

import com.acoderx.im.redis.RedisKey;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import java.util.Collection;
import java.util.Set;

/**
 * Created by xudi on 17-4-29.
 */
public interface ZSetOperation {
    Long intersectAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey);

    Long intersectAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey);

    Long unionAndStore(RedisKey key, RedisKey otherKey, RedisKey destKey);

    Long unionAndStore(RedisKey key, Collection<RedisKey> otherKeys, RedisKey destKey);

    Set<String> range(RedisKey key, long start, long end);

    Set<String> reverseRange(RedisKey key, long start, long end);

    Set<TypedTuple<String>> rangeWithScores(RedisKey key, long start, long end);

    Set<TypedTuple<String>> reverseRangeWithScores(RedisKey key, long start, long end);

    Set<String> rangeByScore(RedisKey key, double min, double max);

    Set<String> rangeByScore(RedisKey key, double min, double max, long offset, long count);

    Set<String> reverseRangeByScore(RedisKey key, double min, double max);

    Set<String> reverseRangeByScore(RedisKey key, double min, double max, long offset, long count);

    Set<TypedTuple<String>> rangeByScoreWithScores(RedisKey key, double min, double max);

    Set<TypedTuple<String>> rangeByScoreWithScores(RedisKey key, double min, double max, long offset, long count);

    Set<TypedTuple<String>> reverseRangeByScoreWithScores(RedisKey key, double min, double max);

    Set<TypedTuple<String>> reverseRangeByScoreWithScores(RedisKey key, double min, double max, long offset, long count);

    Boolean add(RedisKey key, String value, double score);

    Long add(RedisKey key, Set<TypedTuple<String>> tuples);

    Double incrementScore(RedisKey key, String value, double delta);

    Long rank(RedisKey key, String o);

    Long reverseRank(RedisKey key, String o);

    Double score(RedisKey key, String o);

    Long remove(RedisKey key, String... values);

    Long removeRange(RedisKey key, long start, long end);

    Long removeRangeByScore(RedisKey key, double min, double max);

    Long count(RedisKey key, double min, double max);
}
