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

    @Autowired
    ConditionService conditionService;

    private boolean judgeCondition (Condition condition,String tableID) throws ClassNotFoundException, NullPointerException, FieldException, InstantiationException, IllegalAccessException {
        Operand operand = operandService.getById(condition.getsOperand());
        operand.tableID = tableID;
        String dOperand = condition.getdOperand();
        Integer operator = condition.getOperator();
        String typeName = fieldService.selectFieldType(operand.tableName,operand.fieldName);
        if (typeName != null) {
            Object value = fieldService.selectField(operand.tableName, operand.fieldName, operand.tableID);
            if (typeName.contains("bigint")) {
                Long value5 = (Long) value;
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
            } else if (typeName.contains("double")) {
                Double value3 = (Double) value;
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
            } else if (typeName.contains("date")) {
                Date value2 = (Date) value;
                Date dValue2 = JSON.parseObject(dOperand,Date.class);
                return compareDate(operator, value2, dValue2);
            } else if (typeName.contains("varchar")) {
                String value6 = (String) value;
                switch (operator) {
                    case Operator.BE_EQUAL_TO:
                        return value6.equals(dOperand);
                    case Operator.NOT_EQUAL_TO:
                        return !value6.equals(dOperand);
                    case Operator.CONTAIN:
                        return value6.contains(dOperand);
                    case Operator.NOT_CONTAIN:
                        return !value6.contains(dOperand);
                    default:
                        throw new FieldException("operand does not match operator");
                }
            } else if (typeName.contains("int")) {
                Integer value4 = (Integer) value;
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
            } else {
                throw new FieldException("not found the data type");
            }
        } else {
            List<ExpandField> expandFields = expandFieldService.selectField(operand.tableID, operand.tableName, operand.fieldName);
            if (expandFields == null || expandFields.size() != 1) {
                throw new FieldException("the num of expandFields exception");
            }
            String jsonValue = expandFields.get(0).getJsonValue();
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

    private void fillResult (Element element,String tableID) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Condition condition = conditionService.getById(element.conditionOrOperator);
        element.result = this.judgeCondition(condition,tableID);
    }

    public boolean policyDecision (String id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Element> list = elementService.list();
        return this.judgeFormula(list,id);
    }

    private boolean judgeFormula (List<Element> formula, String tableID) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Element element : formula) {
            if (element.isOperand) {
                this.fillResult(element,tableID);
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
                switch (element.conditionOrOperator) {
                    case Operator.AND:
                        result = new Element();
                        result.isOperand = true;
                        result.result = num1.result && num2.result;
                        break;
                    case Operator.OR:
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
                Condition condition = conditionService.getById(element.conditionOrOperator);
                int operator = condition.getOperator();
                String dOperand = condition.getdOperand();
                Operand sOperand = operandService.getById(condition.getsOperand());
                String typeName = fieldService.selectFieldType(sOperand.tableName,sOperand.fieldName);
                boolean flag = operator != Operator.GREATER_THAN && operator != Operator.LESS_THAN && operator != Operator.BE_EQUAL_TO
                        && operator != Operator.NOT_EQUAL_TO && operator != Operator.GREATER_BE_EQUAL && operator != Operator.LESS_BE_EQUAL;
                boolean flagVar = operator != Operator.LESS_BE_EQUAL && operator != Operator.NOT_EQUAL_TO
                        && operator != Operator.CONTAIN && operator != Operator.NOT_CONTAIN;
                if (typeName != null) {
                    if (typeName.contains("bigint")) {
                        JSON.parseObject(dOperand,Long.class);
                        if (flag) {
                            throw new FieldException("the operand and operator not matching");
                        }
                    } else if (typeName.contains("date")) {
                        JSON.parseObject(dOperand,Date.class);
                        if (flag) {
                            throw new FieldException("the operand and operator not matching");
                        }
                    } else if (typeName.contains("double")) {
                        JSON.parseObject(dOperand,Double.class);
                        if (flag) {
                            throw new FieldException("the operand and operator not matching");
                        }
                    } else if (typeName.contains("varchar")) {
                        JSON.parseObject(dOperand,String.class);
                        if (flagVar) {
                            throw new FieldException("the operand and operator not matching");
                        }
                    } else if (typeName.contains("int")) {
                        JSON.parseObject(dOperand,Integer.class);
                        if (flag) {
                            throw new FieldException("the operand and operator not matching");
                        }
                    } else {
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
                                break;
                            case "java.util.Date":
                            case "java.lang.Double":
                            case "java.lang.Integer":
                            case "java.lang.Long":
                                if (flag) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                                break;
                            case "java.lang.String":
                                if (flagVar) {
                                    throw new FieldException("the operand and operator not matching");
                                }
                                break;
                            default:
                                throw new FieldException("not found the data type");
                        }
                    } else {
                        throw new FieldException("typeName is null");
                    }
                }
            } else {
                if (!(element.conditionOrOperator==Operator.AND) && !(element.conditionOrOperator==Operator.OR)) {
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
