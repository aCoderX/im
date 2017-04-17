package com.acoderx.im.data.mysql.dao;

import com.acoderx.im.data.mysql.model.SetValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by xudi on 17-4-17.
 */
@Transactional
public interface SetValueDao {
    @Select("select * from ${setValue.tableName} where `key` = #{setValue.key}")
    Set<SetValue> getSetMembers(@Param(value = "setValue") SetValue setValue);
}
