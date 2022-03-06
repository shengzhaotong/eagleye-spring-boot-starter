package com.vbyte.decisionengine.rules;

/**
 * 这个类代表所有运算符
 * */
public final class Operator {

    /**
     * 大于
     * */
    public static final byte GREATER_THAN = 0;

    /**
     * 小于
     * */
    public static final byte LESS_THAN = 1;

    /**
     * 等于
     * */
    public static final byte BE_EQUAL_TO = 2;

    /**
     * 不等于
     * */
    public static final byte NOT_EQUAL_TO = 3;

    /**
     * 大于等于
     * */
    public static final byte GREATER_BE_EQUAL = 4;

    /**
     * 小于等于
     * */
    public static final byte LESS_BE_EQUAL = 5;

    /**
     * 包含
     * */
    public static final byte CONTAIN = 6;

    /**
     * 不包含
     * */
    public static final byte NOT_CONTAIN = 7;

    /**
     * 与
     * */
    public static final byte AND = 8;

    /**
     * 或
     * */
    public static final byte OR = 9;

}
