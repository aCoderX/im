package com.acoderx.im.data.operation;

import com.acoderx.im.data.redis.interf.*;
import com.acoderx.im.data.redis.proxy.*;
import com.acoderx.im.redis.RedisKey;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;

/**
 * Created by xudi on 2017/2/12.
 */
public class RedisOps {
    private static RedisOps redisOps;
    public static RedisOps getInstance(){
        if(redisOps==null){
            synchronized (RedisOps.class){
                if(redisOps==null){
                    redisOps = new RedisOps();
                }
            }
        }
        return redisOps;
    }

    private RedisTemplate<String, String> redisTemplate;

    private RedisOps(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dataoperation_redis.xml");
        redisTemplate = (RedisTemplate<String, String>) context.getBean("redisTemplate");
    }

    private HashOperation hashOps;
    private SetOperation setOps;
    private ValueOperation valueOps;
    private KeyOperation keyOps;
    private ZSetOperation zsetOps;
    public HashOperation opsForHash(){
        if (hashOps == null) {
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            hashOps = new HashOperationsProx(hashOperations);
        }
        return hashOps;
    }
    public SetOperation opsForSet(){
        if (setOps == null) {
            SetOperations setOperations = redisTemplate.opsForSet();
            setOps = new SetOperationsProxy(setOperations);
        }
        return setOps;
    }
    public ValueOperation opsForValue(){
        if(valueOps == null){
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOps = new ValueOperationsProxy(valueOperations);
        }
        return valueOps;
    }
    public KeyOperation opsForKey(){
        if(keyOps == null){
            keyOps = new KeyOperationsProxy(redisTemplate);
        }
        return keyOps;
    }
    public ZSetOperation opsForZset(){
        if(zsetOps == null){
            ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
            zsetOps = new ZSetOperationProx(zSetOperations);
        }
        return zsetOps;
    }
}
