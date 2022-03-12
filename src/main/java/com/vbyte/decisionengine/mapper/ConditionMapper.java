package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vbyte.decisionengine.rules.Condition;

@Deprecated
public interface ConditionMapper extends BaseMapper<Condition> {

    int createTable();

}
