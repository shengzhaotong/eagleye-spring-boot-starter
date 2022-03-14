package com.vbyte.decisionengine.rules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("element")
public class Element implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    public Integer id;

    @TableField("condition_or_operator")
    public Integer conditionOrOperator;

    @TableField("is_operand")
    public Boolean isOperand;

    @TableField(exist = false)
    public Boolean result;

    public Element() {
    }

    public Element(Integer id, Integer conditionOrOperator, Boolean isOperand, Boolean result) {
        this.id = id;
        this.conditionOrOperator = conditionOrOperator;
        this.isOperand = isOperand;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConditionOrOperator() {
        return conditionOrOperator;
    }

    public void setConditionOrOperator(Integer conditionOrOperator) {
        this.conditionOrOperator = conditionOrOperator;
    }

    public Boolean getOperand() {
        return isOperand;
    }

    public void setOperand(Boolean operand) {
        isOperand = operand;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", conditionOrOperator=" + conditionOrOperator +
                ", isOperand=" + isOperand +
                ", result=" + result +
                '}';
    }

}
