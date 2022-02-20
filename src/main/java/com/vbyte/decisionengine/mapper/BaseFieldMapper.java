package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BaseFieldMapper<E> extends BaseMapper<E> {

    List<Long> search (String tableName,String key);

}

