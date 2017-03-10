package com.acoderx.im.data.redis.interf;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.acoderx.im.redis.RedisKey;

public interface HashOperation {

	void delete(RedisKey key, String... hashKeys);

	Boolean hasKey(RedisKey key, String hashKey);

	String get(RedisKey key, String hashKey);

	List<String> multiGet(RedisKey key, Collection<String> hashKeys);

	Long increment(RedisKey key, String hashKey, long delta);

	Double increment(RedisKey key, String hashKey, double delta);

	Set<String> keys(RedisKey key);

	Long size(RedisKey key);

	void putAll(RedisKey key, Map<String, String> m);

	void put(RedisKey key, String hashKey, String value);

	Boolean putIfAbsent(RedisKey key, String hashKey, String value);

	List<String> values(RedisKey key);

}
