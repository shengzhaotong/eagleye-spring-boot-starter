package com.vbyte.decisionengine.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vbyte.decisionengine.exception.FieldException;
import com.vbyte.decisionengine.mapper.OperandMapper;
import com.vbyte.decisionengine.rules.Operand;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class OperandService extends ServiceImpl<OperandMapper, Operand> {

    @Autowired
    protected FieldService fieldService;

    @Override
    public boolean save(Operand entity) {
        entity.typeName = fieldService.selectFieldType(entity.tableName,entity.fieldName);
        if (entity.typeName == null) {
            entity.typeName = fieldService.selectExpandFieldType(entity.tableName,entity.fieldName);
        }
        if (entity.typeName == null) {
            throw new FieldException("not found field type");
        }
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Operand> entityList) {
        entityList.forEach((entity)->{
            entity.typeName = fieldService.selectFieldType(entity.tableName,entity.fieldName);
            if (entity.typeName == null) {
                entity.typeName = fieldService.selectExpandFieldType(entity.tableName,entity.fieldName);
            }
            if (entity.typeName == null) {
                throw new FieldException("not found field type");
            }
        });
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
    public boolean updateById(Operand entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean updateBatchById(Collection<Operand> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public Operand getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public List<Operand> listByIds(Collection<? extends Serializable> idList) {
        return super.listByIds(idList);
    }

    @Override
    public int count() {
        return super.count();
    }
}
