package com.vbyte.decisionengine.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.exception.FieldException;
import com.vbyte.decisionengine.fields.ExpandField;
import com.vbyte.decisionengine.mapper.ExpandFieldMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class ExpandFieldService extends ServiceImpl<ExpandFieldMapper, ExpandField> {

    public int judge (String tableName,String fieldName, String tableId) {
        return this.baseMapper.judge(tableName,fieldName,tableId);
    }

    private void judgeRepetition(Collection<ExpandField> entityList, int num) {
        int distinctGroupSize = (int) entityList.stream().distinct().count();
        if (entityList.size() > distinctGroupSize) {
            throw new FieldException("the tableName and fieldName id used");
        }
        for (ExpandField entity : entityList) {
            if ( this.judge(entity.getTableName(),entity.getName(),entity.getTableId()) > num ) {
                throw new FieldException("the tableName and fieldName id used");
            }
        }
    }

    public List<ExpandField> selectField (Serializable id, String tableName, String fieldName) {
        QueryWrapper<ExpandField> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id",id);
        wrapper.eq("table_name",tableName);
        wrapper.eq("name",fieldName);
        return this.baseMapper.selectList(wrapper);
    }

    public List<ExpandField> selectFields (List<Long> list,String tableName) {
        QueryWrapper<ExpandField> wrapper = new QueryWrapper<>();
        wrapper.in("table_id",list);
        wrapper.eq("table_name",tableName);
        return this.baseMapper.selectList(wrapper);
    }

    public List<ExpandField> selectFields (Serializable id,String tableName) {
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

    public ExpandFieldService() {
        super();
    }

    @Override
    public boolean save(ExpandField entity) {
        if ( this.judge(entity.getTableName(),entity.getName(),entity.getTableId()) > 0 ) {
            throw new FieldException("the tableName and the fieldName and the tableId is used");
        }
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<ExpandField> entityList) {
        judgeRepetition(entityList,0);
        return super.saveBatch(entityList);
    }

    @Override
    @Deprecated
    public boolean saveOrUpdateBatch(Collection<ExpandField> entityList) {
        judgeRepetition(entityList,0);
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean updateById(ExpandField entity) {
        if ( this.judge(entity.getTableName(),entity.getName(),entity.getTableId()) > 1 ) {
            throw new FieldException("the tableName and the fieldName and the tableId is used");
        }
        return super.updateById(entity);
    }

    @Override
    @Deprecated
    public boolean update(Wrapper<ExpandField> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @Deprecated
    public boolean update(ExpandField entity, Wrapper<ExpandField> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<ExpandField> entityList) {
        judgeRepetition(entityList,1);
        return super.updateBatchById(entityList);
    }

    @Override
    @Deprecated
    public KtUpdateChainWrapper<ExpandField> ktUpdate() {
        return super.ktUpdate();
    }

    @Override
    @Deprecated
    public UpdateChainWrapper<ExpandField> update() {
        return super.update();
    }

    @Override
    @Deprecated
    public LambdaUpdateChainWrapper<ExpandField> lambdaUpdate() {
        return super.lambdaUpdate();
    }

    @Override
    @Deprecated
    public boolean saveOrUpdate(ExpandField entity, Wrapper<ExpandField> updateWrapper) {
        return super.saveOrUpdate(entity, updateWrapper);
    }

}
