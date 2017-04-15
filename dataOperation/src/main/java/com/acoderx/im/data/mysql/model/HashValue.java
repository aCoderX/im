package com.acoderx.im.data.mysql.model;

/**
 * Created by xudi on 2017/2/14.
 */
public class HashValue {
    private String tableName;
    private String key;
    private String field;
    private String value;

    public HashValue() {

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
