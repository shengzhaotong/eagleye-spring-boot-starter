package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vbyte.decisionengine.entity.FieldTable;

@Deprecated
public interface FieldMapper extends BaseMapper<FieldTable> {

    int fields ();
    int fieldsMsg ();
    int judge (String tableName, String fieldName);

    String selectFieldType(String tableName,String fieldName);
    String selectExpandFieldType(String tableName, String fieldName);

}
