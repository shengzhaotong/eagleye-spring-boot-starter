package com.vbyte.decisionengine.fields;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

public abstract class BaseField<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    /**
     * 字段名
     * */
    @TableField("name")
    protected String name;

    /**
     * 字段类型
     * */
    @TableField(exist = false)
    protected final Class<T> type;

    /**
     * 字段类型的名称
     * */
    @TableField("type_name")
    protected final String typeName;

    /**
     * 表名
     * */
    @TableField("table_name")
    protected String tableName;

    /**
     * 该字段信息所属于该表的第table_id条字段
     * */
    @TableField("table_id")
    protected Long tableId;

    /**
     * 字段值
     * */
    @TableField(exist = false)
    protected T value;

    /**
     * 字段值经过json序列化之后的值
     * */
    @TableField("json_value")
    protected String jsonValue;

    {
        type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        typeName = type.getName();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<T> getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.jsonValue = JSON.toJSONString(value);
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
        this.value = JSON.parseObject(jsonValue,type);
    }

    @Override
    public String toString() {
        return "BaseField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableId=" + tableId +
                ", value=" + value +
                ", jsonValue='" + jsonValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseField)) return false;
        BaseField<?> baseField = (BaseField<?>) o;
        return Objects.equals(getId(), baseField.getId())
                && Objects.equals(getName(), baseField.getName())
                && Objects.equals(getType(), baseField.getType())
                && Objects.equals(getTypeName(), baseField.getTypeName())
                && Objects.equals(getTableName(), baseField.getTableName())
                && Objects.equals(getTableId(), baseField.getTableId()) &&
                Objects.equals(getValue(), baseField.getValue())
                && Objects.equals(getJsonValue(), baseField.getJsonValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), getTypeName(), getTableName(), getTableId(), getValue(), getJsonValue());
    }

}
