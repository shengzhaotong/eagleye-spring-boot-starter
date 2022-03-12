package com.vbyte.decisionengine.config;

import com.vbyte.decisionengine.mapper.*;
import com.vbyte.decisionengine.rules.Formula;
import com.vbyte.decisionengine.service.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FieldConfiguration {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate template;

    @Autowired
    public void setTemplate (SqlSessionTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Bean
    public FieldService fieldService () {
        return new FieldService();
    }

    @Bean
    public ExpandFieldService expandFieldService () {
        return new ExpandFieldService();
    }

    @Bean
    public Formula formula () {
        return new Formula();
    }

    @Bean
    public ElementService elementService () {
        return new ElementService();
    }

    @Bean
    public OperandService operandService () {
        return new OperandService();
    }

    @Bean
    public ConditionService conditionService () {
        return new ConditionService();
    }

    @Bean
    public ConditionMapper conditionMapper () {
        sqlSessionFactory.getConfiguration().addMapper(ConditionMapper.class);
        ConditionMapper mapper = template.getMapper(ConditionMapper.class);
        int table = mapper.createTable();
        return mapper;
    }

    @Bean
    public OperandMapper operandMapper () {
        sqlSessionFactory.getConfiguration().addMapper(OperandMapper.class);
        OperandMapper mapper = template.getMapper(OperandMapper.class);
        int table = mapper.createTable();
        return mapper;
    }

    @Bean
    public ElementMapper elementMapper () {
        sqlSessionFactory.getConfiguration().addMapper(ElementMapper.class);
        ElementMapper mapper = template.getMapper(ElementMapper.class);
        int table = mapper.createTable();
        return mapper;
    }

    @Bean
    public ExpandFieldMapper expandFieldMapper () {
        sqlSessionFactory.getConfiguration().addMapper(ExpandFieldMapper.class);
        return template.getMapper(ExpandFieldMapper.class);
    }

    @Bean
    public FieldMapper fieldMapper () {
        sqlSessionFactory.getConfiguration().addMapper(FieldMapper.class);
        FieldMapper mapper = template.getMapper(FieldMapper.class);
        int fields = mapper.fields();
        int i = mapper.fieldsMsg();
        return mapper;
    }

}

