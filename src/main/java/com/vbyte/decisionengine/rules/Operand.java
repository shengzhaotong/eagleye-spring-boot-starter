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
    @TableField("table_id")
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

}
