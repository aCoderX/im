package com.acoderx.im.data.mysql.model;

/**
 * Created by xudi on 17-4-17.
 */
public class SetValue {
    private String tableName;
    private String key;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SetValue(String tableName, String key) {
        this.tableName = tableName;
        this.key = key;
    }
    public SetValue(){}
}
