package com.vbyte.decisionengine.rules;

import com.alibaba.fastjson.JSON;
import com.vbyte.decisionengine.exception.FieldException;
import com.vbyte.decisionengine.fields.ExpandField;
import com.vbyte.decisionengine.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Stack;

public class Formula {

    @Autowired
    protected FieldService fieldService;

    @Autowired
    protected ExpandFieldService expandFieldService;

    @Autowired
    protected ElementService elementService;

    @Autowired
    protected OperandService operandService;

    public boolean judgeCondition (Condition condition) throws ClassNotFoundException, NullPointerException, FieldException, InstantiationException, IllegalAccessException {
        Operand operand = operandService.getById(condition.getsOperand());
        String dOperand = condition.getdOperand();
        int operator = condition.getOperator();
        String typeName = fieldService.selectFieldType(operand.tableName,operand.fieldName);
        List<ExpandField> expandFields = expandFieldService.selectField(operand.tableID, operand.tableName, operand.fieldName);
        if (expandFields == null || expandFields.size() != 1) {
            throw new FieldException("the num of expandFields exception");
        }
        String jsonValue = expandFields.get(0).getJsonValue();
        if (typeName != null) {
            switch (typeName) {
                case "tinyint":
                    Boolean value1 = JSON.parseObject(jsonValue,Boolean.class);
                    Boolean dValue1 = JSON.parseObject(dOperand,Boolean.class);
                    if (operator == Operator.BE_EQUAL_TO) {
                        return value1.equals(dValue1);
                    } else if (operator == Operator.NOT_EQUAL_TO) {
                        return !value1.equals(dValue1);
                    } else {
                        throw new FieldException("operand does not match operator");
                    }
                case "datetime":
                    Date value2 = JSON.parseObject(jsonValue, Date.class);
                    Date dValue2 = JSON.parseObject(dOperand,Date.class);
                    return compareDate(operator, value2, dValue2);
                case "double":
                    Double value3 = JSON.parseObject(jsonValue,Double.class);
                    Double dValue3 = JSON.parseObject(dOperand,Double.class);
                    switch (operator) {
                        case Operator.GREATER_THAN:
                            return value3.compareTo(dValue3) > 0;
                        case Operator.LESS_THAN:
                            return value3.compareTo(dValue3) < 0;
                        case Operator.BE_EQUAL_TO:
                            return value3.equals(dValue3);
                        case Operator.NOT_EQUAL_TO:
                            return !value3.equals(dValue3);
                        case Operator.GREATER_BE_EQUAL:
                            return value3.compareTo(dValue3) >= 0;
                        case Operator.LESS_BE_EQUAL:
                            return value3.compareTo(dValue3) <= 0;
                        default:
                            throw new FieldException("operand does not match operator");
                    }
                case  "int":
                    Integer value4 = JSON.parseObject(jsonValue,Integer.class);
                    Integer dValue4 = JSON.parseObject(dOperand,Integer.class);
                    switch (operator) {
                        case Operator.GREATER_THAN:
                            return value4.compareTo(dValue4) > 0;
                        case Operator.LESS_THAN:
                            return value4.compareTo(dValue4) < 0;
                        case Operator.BE_EQUAL_TO:
                            return value4.equals(dValue4);
                        case Operator.NOT_EQUAL_TO:
                            return !value4.equals(dValue4);
                        case Operator.GREATER_BE_EQUAL:
                            return value4.compareTo(dValue4) >= 0;
                        case Operator.LESS_BE_EQUAL:
                            return value4.compareTo(dValue4) <= 0;
                        default:
                            throw new FieldException("operand does not match operator");
                    }
                case "bigint":
                    Long value5 = JSON.parseObject(jsonValue,Long.class);
                    Long dValue5 = JSON.parseObject(dOperand,Long.class);
                    switch (operator) {
                        case Operator.GREATER_THAN:
                            return value5.compareTo(dValue5) > 0;
                        case Operator.LESS_THAN:
                            return value5.compareTo(dValue5) < 0;
                        case Operator.BE_EQUAL_TO:
                            return value5.equals(dValue5);
                        case Operator.NOT_EQUAL_TO:
                            return !value5.equals(dValue5);
                        case Operator.GREATER_BE_EQUAL:
                            return value5.compareTo(dValue5) >= 0;
                        case Operator.LESS_BE_EQUAL:
                            return value5.compareTo(dValue5) <= 0;
                        default:
                            throw new FieldException("operand does not match operator");
                    }
                case "varchar(255)":
                    String value6 = JSON.parseObject(jsonValue,String.class);
                    String dValue6 = JSON.parseObject(dOperand,String.class);
                    switch (operator) {
                        case Operator.BE_EQUAL_TO:
                            return value6.equals(dValue6);
                        case Operator.NOT_EQUAL_TO:
                            return !value6.equals(dValue6);
                        case Operator.CONTAIN:
                            return value6.contains(dValue6);
                        case Operator.NOT_CONTAIN:
                            return !value6.contains(dValue6);
                        default:
                            throw new FieldException("operand does not match operator");
                    }
                default:
                    throw new FieldException("not found the data type");
            }
        } else {
            typeName = fieldService.selectExpandFieldType(operand.tableName,operand.fieldName);
            if (typeName != null) {
                switch (typeName) {
                    case "java.lang.Boolean":
                        Boolean value1 = JSON.parseObject(jsonValue,Boolean.class);
                        Boolean dValue1 = JSON.parseObject(dOperand,Boolean.class);
                        if (operator == Operator.BE_EQUAL_TO) {
                            return value1.equals(dValue1);
                        } else if (operator == Operator.NOT_EQUAL_TO) {
                            return !value1.equals(dValue1);
                        } else {
                            throw new FieldException("operand does not match operator");
                        }
                    case "java.util.Date":
                        Date value2 = JSON.parseObject(jsonValue, Date.class);
                        Date dValue2 = JSON.parseObject(dOperand,Date.class);
                        return compareDate(operator, value2, dValue2);
                    case "java.lang.Double":
                        Double value3 = JSON.parseObject(jsonValue,Double.class);
                        Double dValue3 = JSON.parseObject(dOperand,Double.class);
                        switch (operator) {
                            case Operator.GREATER_THAN:
                                return value3.compareTo(dValue3) > 0;
                            case Operator.LESS_THAN:
                                return value3.compareTo(dValue3) < 0;
                            case Operator.BE_EQUAL_TO:
                                return value3.equals(dValue3);
                            case Operator.NOT_EQUAL_TO:
                                return !value3.equals(dValue3);
                            case Operator.GREATER_BE_EQUAL:
                                return value3.compareTo(dValue3) >= 0;
                            case Operator.LESS_BE_EQUAL:
                                return value3.compareTo(dValue3) <= 0;
                            default:
                                throw new FieldException("operand does not match operator");
                        }
                    case  "java.lang.Integer":
                        Integer value4 = JSON.parseObject(jsonValue,Integer.class);
                        Integer dValue4 = JSON.parseObject(dOperand,Integer.class);
                        switch (operator) {
                            case Operator.GREATER_THAN:
                                return value4.compareTo(dValue4) > 0;
                            case Operator.LESS_THAN:
                                return value4.compareTo(dValue4) < 0;
                            case Operator.BE_EQUAL_TO:
                                return value4.equals(dValue4);
                            case Operator.NOT_EQUAL_TO:
                                return !value4.equals(dValue4);
                            case Operator.GREATER_BE_EQUAL:
                                return value4.compareTo(dValue4) >= 0;
                            case Operator.LESS_BE_EQUAL:
                                return value4.compareTo(dValue4) <= 0;
                            default:
                                throw new FieldException("operand does not match operator");
                        }
                    case "java.lang.Long":
                        Long value5 = JSON.parseObject(jsonValue,Long.class);
                        Long dValue5 = JSON.parseObject(dOperand,Long.class);
                        switch (operator) {
                            case Operator.GREATER_THAN:
                                return value5.compareTo(dValue5) > 0;
                            case Operator.LESS_THAN:
                                return value5.compareTo(dValue5) < 0;
                            case Operator.BE_EQUAL_TO:
                                return value5.equals(dValue5);
                            case Operator.NOT_EQUAL_TO:
                                return !value5.equals(dValue5);
                            case Operator.GREATER_BE_EQUAL:
                                return value5.compareTo(dValue5) >= 0;
                            case Operator.LESS_BE_EQUAL:
                                return value5.compareTo(dValue5) <= 0;
                            default:
                                throw new FieldException("operand does not match operator");
                        }
                    case "java.lang.String":
                        String value6 = JSON.parseObject(jsonValue,String.class);
                        String dValue6 = JSON.parseObject(dOperand,String.class);
                        switch (operator) {
                            case Operator.BE_EQUAL_TO:
                                return value6.equals(dValue6);
                            case Operator.NOT_EQUAL_TO:
                                return !value6.equals(dValue6);
                            case Operator.CONTAIN:
                                return value6.contains(dValue6);
                            case Operator.NOT_CONTAIN:
                                return !value6.contains(dValue6);
                            default:
                                throw new FieldException("operand does not match operator");
                        }
                    default:
                        throw new FieldException("not found the data type");
                }
            } else {
                throw new FieldException("typeName is null");
            }
        }
    }

    private boolean compareDate(int operator, Date value2, Date dValue2) {
        switch (operator) {
            case Operator.GREATER_THAN:
                return value2.getTime() > dValue2.getTime();
            case Operator.LESS_THAN:
                return value2.getTime() < dValue2.getTime();
            case Operator.BE_EQUAL_TO:
                return value2.getTime() == dValue2.getTime();
            case Operator.NOT_EQUAL_TO:
                return value2.getTime() != dValue2.getTime();
            case Operator.GREATER_BE_EQUAL:
                return value2.getTime() >= dValue2.getTime();
            case Operator.LESS_BE_EQUAL:
                return value2.getTime() <= dValue2.getTime();
            default:
                throw new FieldException("operand does not match operator");
        }
    }

    private void fillResult (Element element) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Condition condition = JSON.parseObject(element.item,Condition.class);
        element.result = this.judgeCondition(condition);
    }

    public boolean judgeFormula (Element[] formula) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Element element : formula) {
            if (element.isOperand) {
                this.fillResult(element);
            }
        }
        Stack<Element> stack = new Stack<>();
        Element result = null;
        for (Element element : formula) {
            //当s为数字时直接压栈
            if (element.isOperand) {
                stack.push(element);
            } else {
                //为运算符时弹出栈顶和次栈顶并进行运算
                Element num2 = stack.pop();
                Element num1 = stack.pop();
                switch (element.item) {
                    case "&":
                        result = new Element();
                        result.isOperand = true;
                        result.result = num1.result && num2.result;
                        break;
                    case "|":
                        result = new Element();
                        result.isOperand = true;
                        result.result = num1.result || num2.result;
                        break;
                    default:
                        throw new RuntimeException("contains illegal characters");
                }
                //将运算结果压入栈
                stack.push(result);
            }
        }
        //返回运算结果
        return stack.pop().result;
    }

    public boolean saveFormula (List<Element> formula) throws ClassNotFoundException, FieldException {
        for (Element element : formula) {
            if (element.isOperand) {
                Condition condition = JSON.parseObject(element.item, Condition.class);
                int operator = condition.getOperator();
                String dOperand = condition.getdOperand();
                Operand sOperand = operandService.getById(condition.getsOperand());
                String typeName = fieldService.selectFieldType(sOperand.tableName,sOperand.fieldName);
                boolean flag = operator != Operator.GREATER_THAN && operator != Operator.LESS_THAN && operator != Operator.BE_EQUAL_TO
                        && operator != Operator.NOT_EQUAL_TO && operator != Operator.GREATER_BE_EQUAL && operator != Operator.LESS_BE_EQUAL;
                boolean flagVar = operator != Operator.LESS_BE_EQUAL && operator != Operator.NOT_EQUAL_TO
                        && operator != Operator.CONTAIN && operator != Operator.NOT_CONTAIN;
                if (typeName != null) {
                    switch (typeName) {
                        case "tinyint":
                            JSON.parseObject(dOperand, Boolean.class);
                            if (operator != Operator.BE_EQUAL_TO && operator != Operator.NOT_EQUAL_TO ) {
                                throw new FieldException("the operand and operator not matching");
                            }
                        case "datetime":
                            JSON.parseObject(dOperand,Date.class);
                            if (flag) {
                                throw new FieldException("the operand and operator not matching");
                            }
                        case "double":
                            JSON.parseObject(dOperand,Double.class);
                            if (flag) {
                                throw new FieldException("the operand and operator not matching");
                            }
                        case  "int":
                            JSON.parseObject(dOperand,Integer.class);
                            if (flag) {
                                throw new FieldException("the operand and operator not matching");
                            }
                        case "bigint":
                            JSON.parseObject(dOperand,Long.class);
                            if (flag) {
                                throw new FieldException("the operand and operator not matching");
                            }
                        case "varchar(255)":
                            JSON.parseObject(dOperand,String.class);
                            if (flagVar) {
                                throw new FieldException("the operand and operator not matching");
                            }
                        default:
                            throw new FieldException("not found the data type");
                    }
                } else {
                    typeName = fieldService.selectExpandFieldType(sOperand.tableName, sOperand.fieldName);
                    if (typeName != null) {
                        JSON.parseObject(dOperand,Class.forName(typeName));
                        switch (typeName) {
                            case "java.lang.Boolean":
                                if (operator != Operator.BE_EQUAL_TO && operator != Operator.NOT_EQUAL_TO) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                            case "java.util.Date":
                                if (flag) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                            case "java.lang.Double":
                                if (flag) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                            case "java.lang.Integer":
                                if (flag) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                            case "java.lang.Long":
                                if (flag) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                            case "java.lang.String":
                                if (flagVar) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                            default:
                                throw new FieldException("not found the data type");
                        }
                    } else {
                        throw new FieldException("typeName is null");
                    }
                }
            } else {
                if (!"&".equals(element.item) && !"|".equals(element.item)) {
                    throw new FieldException("Illegal operator");
                }
            }
        }
        elementService.deleteAll();
        boolean result = true;
        int size = formula.size();
        for (int i = 0; i < size; i++) {
            Element element = formula.get(i);
            element.id = i+1;
            result &= elementService.save(element);
        }
        return result;
    }

}
