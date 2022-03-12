package com.vbyte.decisionengine.rules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("condition")
public class Condition {

    /**
     * 主键id
     * */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 源操作数id
     * */
    @TableField("s_operand")
    private Integer sOperand;

    /**
     * 目标操作数
     * */
    @TableField("d_operand")
    private String dOperand;

    /**
     * 操作符
     * */
    @TableField("operator")
    private int operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setsOperand(Integer sOperand) {
        this.sOperand = sOperand;
    }

    public Integer getsOperand () {
        return sOperand;
    }

    public String getdOperand() {
        return dOperand;
    }

    public void setdOperand(String dOperand) {
        this.dOperand = dOperand;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

}
