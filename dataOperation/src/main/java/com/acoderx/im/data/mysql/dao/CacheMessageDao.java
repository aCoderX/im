package com.acoderx.im.data.mysql.dao;

import com.acoderx.im.data.mysql.model.CacheMessage;
import com.acoderx.im.data.mysql.model.HashValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by xudi on 2017/2/14.
 */
@Transactional
public interface CacheMessageDao {

    @Insert("insert into cache_message(`sender`,`target`,`sync_no`,`message`) values(#{cacheMessage.sender},#{cacheMessage.target},#{cacheMessage.syncNo},#{cacheMessage.message})")
    void setCacheMessage(@Param(value = "cacheMessage") CacheMessage cacheMessage);

    @Select("select max(sync_no) from cache_message where sender=#{sender}")
    int countSycnNOBySender(@Param(value = "sender") int sender);

    @Delete("delete from cache_message where `sender`=#{sender} and `syncNo`<=#{syncNo}")
    void finBeforeCacheMessage(@Param(value = "sender")int sender,@Param(value = "syncNo")int syncNo);

    @Select("select max(sync_no) from cache_message where sender=#{sender}")
    List<CacheMessage> syncCacheMessage(@Param(value = "sender") int sender,@Param(value = "syncNo")int syncNo);
}
