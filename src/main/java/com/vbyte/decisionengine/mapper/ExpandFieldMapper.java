package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vbyte.decisionengine.fields.ExpandField;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Deprecated
public interface ExpandFieldMapper extends BaseMapper<ExpandField> {

    List<String> search (String tableName,String key);
    int judge(String tableName, String fieldName, String tableId);

    @Override
    int insert(ExpandField entity);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteByMap(Map<String, Object> columnMap);

    @Override
    int delete(Wrapper<ExpandField> queryWrapper);

    @Override
    int deleteBatchIds(Collection<? extends Serializable> idList);

    @Override
    int updateById(ExpandField entity);

    @Override
    int update(ExpandField entity, Wrapper<ExpandField> updateWrapper);

    @Override
    ExpandField selectById(Serializable id);

    @Override
    List<ExpandField> selectBatchIds(Collection<? extends Serializable> idList);

    @Override
    List<ExpandField> selectByMap(Map<String, Object> columnMap);

    @Override
    ExpandField selectOne(Wrapper<ExpandField> queryWrapper);

    @Override
    Integer selectCount(Wrapper<ExpandField> queryWrapper);

    @Override
    List<ExpandField> selectList(Wrapper<ExpandField> queryWrapper);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<ExpandField> queryWrapper);

    @Override
    List<Object> selectObjs(Wrapper<ExpandField> queryWrapper);

    @Override
    <E extends IPage<ExpandField>> E selectPage(E page, Wrapper<ExpandField> queryWrapper);

    @Override
    <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<ExpandField> queryWrapper);

}

