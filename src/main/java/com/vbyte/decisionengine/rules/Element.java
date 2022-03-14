package com.vbyte.decisionengine.rules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("element")
public class Element implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;

    @TableField("condition_or_operator")
    public Integer conditionOrOperator;

    @TableField("is_operand")
    public Boolean isOperand;

    @TableField(exist = false)
    public Boolean result;

}
