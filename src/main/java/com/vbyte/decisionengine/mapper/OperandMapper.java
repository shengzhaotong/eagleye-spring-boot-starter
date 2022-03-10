package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vbyte.decisionengine.rules.Operand;

@Deprecated
public interface OperandMapper extends BaseMapper<Operand> {

    int createTable();

}
