package com.vbyte.decisionengine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.entity.FieldTable;
import com.vbyte.decisionengine.mapper.FieldMapper;

import java.util.List;

public class FieldService extends ServiceImpl<FieldMapper, FieldTable> {

    public List<FieldTable> select (String tableName) {
        QueryWrapper<FieldTable> wrapper = new QueryWrapper<>();
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

}
