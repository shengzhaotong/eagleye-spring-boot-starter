package com.vbyte.decisionengine.fields;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

@TableName("fields_msg")
public class ExpandField implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * */
    @TableId(value = "id", type = IdType.AUTO)
    protected String id;

    /**
     * 字段名
     * */
    @TableField("name")
    protected String name;

    /**
     * 字段类型
     * */
    @TableField(exist = false)
    protected Class<?> type;

    /**
     * 字段类型的名称
     * */
    @TableField("type_name")
    protected String typeName;

    /**
     * 表名
     * */
    @TableField("table_name")
    protected String tableName;

    /**
     * 该字段信息所属于该表的第table_id条字段
     * */
    @TableField("table_id")
    protected String tableId;

    /**
     * 字段值
     * */
    @TableField(exist = false)
    public Object value;

    /**
     * 字段值经过json序列化之后的值
     * */
    @TableField("json_value")
    protected String jsonValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeName (String typeName) {
        this.typeName = typeName;
        try {
            this.type = Class.forName(this.typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (this.jsonValue != null) {
            this.value = JSON.parseObject(this.jsonValue,type);
        }
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Class<?> getType() {
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

    public String getTableId() {
        return tableId;
    }

    public Object getValue() {
        return value;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
        if (this.type != null) {
            this.value = JSON.parseObject(this.jsonValue,type);
        }
    }

    @Override
    public String toString() {
        return "BaseField{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableId='" + tableId + '\'' +
                ", value=" + value +
                ", jsonValue='" + jsonValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpandField)) return false;
        ExpandField expandField = (ExpandField) o;
        return Objects.equals(getId(), expandField.getId())
                && Objects.equals(getName(), expandField.getName())
                && Objects.equals(getType(), expandField.getType())
                && Objects.equals(getTypeName(), expandField.getTypeName())
                && Objects.equals(getTableName(), expandField.getTableName())
                && Objects.equals(getTableId(), expandField.getTableId()) &&
                Objects.equals(getValue(), expandField.getValue())
                && Objects.equals(getJsonValue(), expandField.getJsonValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), getTypeName(), getTableName(), getTableId(), getValue(), getJsonValue());
    }

}
