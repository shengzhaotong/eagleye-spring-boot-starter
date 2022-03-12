package com.vbyte.decisionengine.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.mapper.ConditionMapper;
import com.vbyte.decisionengine.rules.Condition;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class ConditionService extends ServiceImpl<ConditionMapper, Condition> {

    @Override
    public boolean save(Condition entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Condition> entityList) {
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
    public boolean updateById(Condition entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean updateBatchById(Collection<Condition> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public Condition getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Condition> list() {
        return super.list();
    }

}
