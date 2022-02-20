package com.vbyte.decisionengine.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.mapper.BaseFieldMapper;

import java.io.Serializable;
import java.util.List;

public class BaseFieldService<M extends BaseFieldMapper<T>, T> extends ServiceImpl<M,T> {

    public List<T> selectFields (List<Long> list) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.in("table_id",list);
        return this.baseMapper.selectList(wrapper);
    }

    public List<T> selectField (Serializable id) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id",id);
        return this.baseMapper.selectList(wrapper);
    }

    public List<T> selectByTable (String tableName) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

    public List<Long> search (String tableName,String key) {
        return this.baseMapper.search(tableName,key);
    }

}
