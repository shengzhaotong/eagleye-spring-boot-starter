package com.vbyte.decisionengine.rules;

public class Condition {

    /**
     * 源操作数
     * */
    private Operand sOperand;

    /**
     * 目标操作数
     * */
    private String dOperand;

    /**
     * 操作符
     * */
    private int operator;

    public Operand getsOperand() {
        return sOperand;
    }

    public void setsOperand(Operand sOperand) {
        this.sOperand = sOperand;
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
