package com.acoderx.im.data.mysql.dao;

import com.acoderx.im.data.mysql.model.HashValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xudi on 2017/2/14.
 */
@Transactional
public interface HashValueDao {
    @Select("select * from ${hashValue.tableName} a where a.key = #{hashValue.key}")
    List<HashValue> test(@Param(value = "hashValue") HashValue hashValue);

    @Select("select * from ${hashValue.tableName} where `key` = #{hashValue.key}")
    List<HashValue> getHashKey(@Param(value = "hashValue") HashValue hashValue);
    //public HashValue getHashValue(HashValue hashValue);

    @Select("select * from ${hashValue.tableName} where `key` = #{hashValue.key} and field = #{hashValue.field}")
    HashValue getHashValue(@Param(value = "hashValue") HashValue hashValue);
}
