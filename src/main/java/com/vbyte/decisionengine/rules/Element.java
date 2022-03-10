package com.vbyte.decisionengine.rules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("element")
public class Element {

    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;

    @TableField("item")
    public String item;

    @TableField("is_operand")
    public Boolean isOperand;

    @TableField(exist = false)
    public Boolean result;

}
