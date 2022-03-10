package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vbyte.decisionengine.fields.ExpandField;

import java.util.List;

@Deprecated
public interface ExpandFieldMapper extends BaseMapper<ExpandField> {

    List<String> search (String tableName,String key);

    int judge(String tableName, String fieldName, String tableId);

}

