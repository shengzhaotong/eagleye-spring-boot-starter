package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vbyte.decisionengine.entity.FieldTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Deprecated
public interface FieldMapper extends BaseMapper<FieldTable> {

    int fields ();
    int fieldsMsg ();
    int judge (String tableName, String fieldName);

    String selectFieldType(String tableName,String fieldName);
    String selectExpandFieldType(String tableName, String fieldName);

    @Override
    int insert(FieldTable entity);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteByMap(Map<String, Object> columnMap);

    @Override
    int delete(Wrapper<FieldTable> queryWrapper);

    @Override
    int deleteBatchIds(Collection<? extends Serializable> idList);

    @Override
    int updateById(FieldTable entity);

    @Override
    int update(FieldTable entity, Wrapper<FieldTable> updateWrapper);

    @Override
    FieldTable selectById(Serializable id);

    @Override
    List<FieldTable> selectBatchIds(Collection<? extends Serializable> idList);

    @Override
    List<FieldTable> selectByMap(Map<String, Object> columnMap);

    @Override
    FieldTable selectOne(Wrapper<FieldTable> queryWrapper);

    @Override
    Integer selectCount(Wrapper<FieldTable> queryWrapper);

    @Override
    List<FieldTable> selectList(Wrapper<FieldTable> queryWrapper);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<FieldTable> queryWrapper);

    @Override
    List<Object> selectObjs(Wrapper<FieldTable> queryWrapper);

    @Override
    <E extends IPage<FieldTable>> E selectPage(E page, Wrapper<FieldTable> queryWrapper);

    @Override
    <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<FieldTable> queryWrapper);

}
