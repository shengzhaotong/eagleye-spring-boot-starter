package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vbyte.decisionengine.fields.ExpandField;

import java.util.List;

public interface ExpandFieldMapper extends BaseMapper<ExpandField> {

    List<String> search (String tableName,String key);

}

