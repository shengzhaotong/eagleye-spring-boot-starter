package com.vbyte.decisionengine.rules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("operand")
public class Operand implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;

    /**
     *数据id
     **/
    @TableField(exist = false)
    public String tableID;

    /**
     * 表名
     * */
    @TableField("table_name")
    public String tableName;

    /**
     * 字段名
     * */
    @TableField("field_name")
    public String fieldName;

    /**
     * 类型名
     * */
    @TableField("type_name")
    public String typeName;

    public Operand() {
    }

    public Operand(Integer id, String tableID, String tableName, String fieldName, String typeName) {
        this.id = id;
        this.tableID = tableID;
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Operand{" +
                "id=" + id +
                ", tableID='" + tableID + '\'' +
                ", tableName='" + tableName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }

}
