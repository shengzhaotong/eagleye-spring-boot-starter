package com.vbyte.decisionengine.rules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("conditions")
public class Condition implements Serializable {

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
    private Integer operator;

    public Condition() {
    }

    public Condition(Integer id, Integer sOperand, String dOperand, Integer operator) {
        this.id = id;
        this.sOperand = sOperand;
        this.dOperand = dOperand;
        this.operator = operator;
    }

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

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", sOperand=" + sOperand +
                ", dOperand='" + dOperand + '\'' +
                ", operator=" + operator +
                '}';
    }

}
