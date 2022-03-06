package com.vbyte.decisionengine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author sheng zhao tong
 * @since 2022-01-11
 */
@TableName("fields")
public class FieldTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字段名
     */
    @TableField("name")
    private String name;

    /**
     * 表名
     */
    @TableField("table_name")
    private String tableName;

    /**
     * 字段类型
     */
    @TableField("type_name")
    private String typeName;

    /**
     * 注释
     */
    @TableField("notes")
    private String notes;

    public FieldTable() {
    }

    public FieldTable(Long id, String name, String typeName, String notes, String tableName) {
        this.id = id;
        this.name = name;
        this.typeName = typeName;
        this.notes = notes;
        this.tableName = tableName;
    }

    public FieldTable(String name, String tableName, String typeName, String notes) {
        this.name = name;
        this.tableName = tableName;
        this.typeName = typeName;
        this.notes = notes;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "FieldTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tableName='" + tableName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public boolean isEqual(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldTable)) return false;
        FieldTable that = (FieldTable) o;
        return getId().equals(that.getId())
                && getName().equals(that.getName())
                && getTableName().equals(that.getTableName())
                && getTypeName().equals(that.getTypeName())
                && getNotes().equals(that.getNotes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldTable)) return false;
        FieldTable that = (FieldTable) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getTableName(), that.getTableName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTableName());
    }

}
