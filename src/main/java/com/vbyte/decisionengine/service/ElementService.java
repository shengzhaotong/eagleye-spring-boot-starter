package com.vbyte.decisionengine.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.mapper.ElementMapper;
import com.vbyte.decisionengine.rules.Element;

import java.io.Serializable;
import java.util.Collection;

public class ElementService extends ServiceImpl<ElementMapper, Element> {

    public int deleteAll () {
        return this.baseMapper.deleteAll();
    }

    @Override
    public boolean save(Element entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Element> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(Element entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean updateBatchById(Collection<Element> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public int count() {
        return super.count();
    }

}
