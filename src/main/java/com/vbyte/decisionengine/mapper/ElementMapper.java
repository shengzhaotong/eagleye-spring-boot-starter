package com.vbyte.decisionengine.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vbyte.decisionengine.rules.Element;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Deprecated
public interface ElementMapper extends BaseMapper<Element> {

    int createTable();

    int deleteAll();

    @Override
    int insert(Element entity);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteByMap(Map<String, Object> columnMap);

    @Override
    int delete(Wrapper<Element> queryWrapper);

    @Override
    int deleteBatchIds(Collection<? extends Serializable> idList);

    @Override
    int updateById(Element entity);

    @Override
    int update(Element entity, Wrapper<Element> updateWrapper);

    @Override
    Element selectById(Serializable id);

    @Override
    List<Element> selectBatchIds(Collection<? extends Serializable> idList);

    @Override
    List<Element> selectByMap(Map<String, Object> columnMap);

    @Override
    Element selectOne(Wrapper<Element> queryWrapper);

    @Override
    Integer selectCount(Wrapper<Element> queryWrapper);

    @Override
    List<Element> selectList(Wrapper<Element> queryWrapper);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<Element> queryWrapper);

    @Override
    List<Object> selectObjs(Wrapper<Element> queryWrapper);

    @Override
    <E extends IPage<Element>> E selectPage(E page, Wrapper<Element> queryWrapper);

    @Override
    <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<Element> queryWrapper);

}
