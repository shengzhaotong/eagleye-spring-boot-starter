package com.vbyte.decisionengine.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.fields.ExpandField;
import com.vbyte.decisionengine.mapper.ExpandFieldMapper;

import java.io.Serializable;
import java.util.List;

public class ExpandFieldService extends ServiceImpl<ExpandFieldMapper, ExpandField> {

    public List<ExpandField> selectFields (List<Long> list,String tableName) {
        QueryWrapper<ExpandField> wrapper = new QueryWrapper<>();
        wrapper.in("table_id",list);
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

    public List<ExpandField> selectField (Serializable id,String tableName) {
        QueryWrapper<ExpandField> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id",id);
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

    public List<ExpandField> selectByTable (String tableName) {
        QueryWrapper<ExpandField> wrapper = new QueryWrapper<>();
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

    public List<String> search (String tableName,String key) {
        return this.baseMapper.search(tableName,key);
    }

}
