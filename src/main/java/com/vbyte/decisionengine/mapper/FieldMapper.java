package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vbyte.decisionengine.entity.FieldTable;

public interface FieldMapper extends BaseMapper<FieldTable> {

    int fields ();
    int fieldsMsg ();

}
