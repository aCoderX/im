package com.acoderx.im.data.redis.proxy;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.data.mysql.dao.SetValueDao;
import com.acoderx.im.data.mysql.model.HashValue;
import com.acoderx.im.data.mysql.model.SetValue;
import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.data.redis.interf.SetOperation;
import com.acoderx.im.entity.LoggerConf;
import com.acoderx.im.redis.RedisKey;
import org.slf4j.Logger;
import org.springframework.data.redis.core.SetOperations;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;


/**
 * Created by xudi on 2017/3/5.
 */
public class SetOperationsProxy implements SetOperation {
    private Logger logger = LoggerConf.getLogger(SetOperationsProxy.class);
    private SetOperations<String,String> setOperations;
    public SetOperationsProxy(SetOperations setOperations){
        this.setOperations=setOperations;
    }

    public Set<String> members(RedisKey key) {
        if(key.syncFlag()){
            if(!RedisOps.getInstance().opsForKey().hasKey(key)){
                logger.info("DATAOPERATION:Set查询mysql:"+key.keyName());
                SetValueDao setValueDao = MySQLOperations.getInstance().getSetValueDao();
                Set<SetValue> members = setValueDao.getSetMembers(new SetValue("SETS_KEY_1",key.keyName()));
                if(!members.isEmpty()){
                    setOperations.add(key.keyName(),members.stream().map(a->a.getValue()).toArray(String[]::new));
                    setOperations.getOperations().expire(key.keyName(),1, TimeUnit.DAYS);
                }
                return setOperations.members(key.keyName());
            }else{
                setOperations.getOperations().expire(key.keyName(),1, TimeUnit.DAYS);
                return setOperations.members(key.keyName());
            }
        }else{
            return setOperations.members(key.keyName());
        }
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
