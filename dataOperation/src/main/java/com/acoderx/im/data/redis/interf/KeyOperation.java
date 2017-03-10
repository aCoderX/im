package com.acoderx.im.data.redis.interf;

import com.acoderx.im.redis.RedisKey;
import org.springframework.data.redis.connection.DataType;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface KeyOperation {

	/**
	 * 仅可删除未同步的key，特殊情况下使用
	 * 
	 * @param key
	 */
	public void delete(String key);

	public void delete(RedisKey key);

	public void delete(Collection<RedisKey> keys);

	public Boolean hasKey(RedisKey key);

	public Boolean expire(RedisKey key, final long timeout, final TimeUnit unit);

	public Boolean expireAt(RedisKey key, final Date date);

	public Long getExpire(RedisKey key);

	public Long getExpire(RedisKey key, final TimeUnit timeUnit);

	public Set<String> keys(String pattern);

	public Boolean persist(RedisKey key);

	public DataType type(RedisKey key);
}
