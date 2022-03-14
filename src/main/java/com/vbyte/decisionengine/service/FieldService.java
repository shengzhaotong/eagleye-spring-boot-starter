package com.vbyte.decisionengine.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.entity.FieldTable;
import com.vbyte.decisionengine.exception.FieldException;
import com.vbyte.decisionengine.mapper.FieldMapper;

import java.util.Collection;
import java.util.List;

public class FieldService extends ServiceImpl<FieldMapper, FieldTable> {

    public int judge (String tableName,String fieldName) {
        return this.baseMapper.judge(tableName,fieldName);
    }

    private void judgeRepetition(Collection<FieldTable> entityList,int num) {
        int distinctGroupSize = (int) entityList.stream().distinct().count();
        if (entityList.size() > distinctGroupSize) {
            throw new FieldException("the tableName and fieldName id used");
        }
        for (FieldTable entity : entityList) {
            if ( this.judge(entity.getTableName(),entity.getName()) > num ) {
                throw new FieldException("the tableName and fieldName id used");
            }
        }
    }

    public Object selectField (String tableName, String fieldName, String tableId) {
        return this.baseMapper.selectField(tableName,fieldName,tableId);
    }

    public String selectFieldType (String tableName,String fieldName) {
        return this.baseMapper.selectFieldType(tableName,fieldName);
    }

    public String selectExpandFieldType(String tableName, String fieldName) {
        return this.baseMapper.selectExpandFieldType(tableName,fieldName);
    }

    public List<FieldTable> select (String tableName) {
        QueryWrapper<FieldTable> wrapper = new QueryWrapper<>();
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean save(FieldTable entity) {
        if ( this.judge(entity.getTableName(),entity.getName()) > 0 ) {
            throw new FieldException("the tableName and fieldName id used");
        }
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<FieldTable> entityList) {
        judgeRepetition(entityList,0);
        return super.saveBatch(entityList);
    }

    @Override
    @Deprecated
    public boolean saveOrUpdateBatch(Collection<FieldTable> entityList) {
        judgeRepetition(entityList,0);
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean updateById(FieldTable entity) {
        if ( this.judge(entity.getTableName(),entity.getName()) > 1 ) {
            throw new FieldException("the tableName and fieldName id used");
        }
        return super.updateById(entity);
    }

    @Override
    @Deprecated
    public boolean update(Wrapper<FieldTable> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @Deprecated
    public boolean update(FieldTable entity, Wrapper<FieldTable> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<FieldTable> entityList) {
        judgeRepetition(entityList,1);
        return super.updateBatchById(entityList);
    }

    @Override
    @Deprecated
    public boolean saveOrUpdate(FieldTable entity, Wrapper<FieldTable> updateWrapper) {
        return super.saveOrUpdate(entity, updateWrapper);
    }

    @Override
    @Deprecated
    public KtUpdateChainWrapper<FieldTable> ktUpdate() {
        return super.ktUpdate();
    }

    @Override
    @Deprecated
    public UpdateChainWrapper<FieldTable> update() {
        return super.update();
    }

    @Override
    @Deprecated
    public LambdaUpdateChainWrapper<FieldTable> lambdaUpdate() {
        return super.lambdaUpdate();
    }


}
