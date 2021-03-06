package com.acoderx.im.data.redis.proxy;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.data.mysql.dao.HashValueDao;
import com.acoderx.im.data.mysql.model.HashValue;
import com.acoderx.im.data.redis.interf.HashOperation;
import com.acoderx.im.entity.LoggerConf;
import com.acoderx.im.redis.RedisKey;
import org.slf4j.Logger;
import org.springframework.data.redis.core.HashOperations;

import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.acoderx.im.redis.RedisDataType.HASH;

/**
 * Created by xudi on 2017/2/12.
 */
public class HashOperationsProx implements HashOperation {
    private Logger logger = LoggerConf.getLogger(HashOperationsProx.class);

    private HashOperations<String, String, String> hashOperations;

    public HashOperationsProx(HashOperations hashOperations){
        this.hashOperations = hashOperations;
    }

    public void delete(RedisKey key, String... hashKeys) {

    }

    public Boolean hasKey(RedisKey key, String hashKey) {
        return null;
    }

    public String get(RedisKey key, String hashKey) {
        if(key.syncFlag()){
            if(this.hashOperations.getOperations().hasKey(key.keyName())==false){
                //redis中没有就去mysql中查
                logger.info("DATAOPERATION:Hash查询mysql:"+key.keyName());
                HashValueDao hashValueDao = MySQLOperations.getInstance().getHashValueDao();
                HashValue hashValue = new HashValue();
                hashValue.setTableName("HASHS_KEY_"+"1");
                hashValue.setKey(key.keyName());
                hashValue.setField(hashKey);
                List<HashValue> list = hashValueDao.getHashKey(hashValue);
                if (list.size()<=0){
                    return null;
                }else{
                    for (HashValue result : list){
                        hashOperations.put(key.keyName(),result.getField(),result.getValue());
                    }
                    hashOperations.getOperations().expire(key.keyName(),1,TimeUnit.DAYS);
                    return hashOperations.get(key.keyName(),hashKey);
                }
            }else{
                hashOperations.getOperations().expire(key.keyName(),1,TimeUnit.DAYS);
                return hashOperations.get(key.keyName(), hashKey);
            }
        }else{
            return hashOperations.get(key.keyName(), hashKey);
        }
    }

    public List<String> multiGet(RedisKey key, Collection<String> hashKeys) {
        return null;
    }

    public Long increment(RedisKey key, String hashKey, long delta) {
        return null;
    }

    public Double increment(RedisKey key, String hashKey, double delta) {
        return null;
    }

    public Set<String> keys(RedisKey key) {
        return null;
    }

    public Long size(RedisKey key) {
        return null;
    }

    public void putAll(RedisKey key, Map<String, String> m) {
        if(key.syncFlag()){
            //需要存入mysql
            logger.info("DATAOPERATION:存入mysql:"+key.keyName());
            HashValueDao hashValueDao = MySQLOperations.getInstance().getHashValueDao();

            for (Map.Entry<String, String> entry : m.entrySet()) {
                HashValue hashValue = new HashValue();
                hashValue.setTableName("HASHS_KEY_"+"1");
                hashValue.setKey(key.keyName());
                hashValue.setField(entry.getKey());
                hashValue.setValue(entry.getValue());
                hashValueDao.setHashValue(hashValue);
            }
            hashOperations.putAll(key.keyName(),m);
            hashOperations.getOperations().expire(key.keyName(),1,TimeUnit.DAYS);
        }else{
            hashOperations.putAll(key.keyName(),m);
        }
    }

    public void put(RedisKey key, String hashKey, String value) {
        hashOperations.put(key.keyName(),hashKey,value);
    }

    public Boolean putIfAbsent(RedisKey key, String hashKey, String value) {
        return null;
    }

    public List<String> values(RedisKey key) {
        return null;
    }
}
