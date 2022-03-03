package com.vbyte.decisionengine.config;

import com.vbyte.decisionengine.mapper.ExpandFieldMapper;
import com.vbyte.decisionengine.mapper.FieldMapper;
import com.vbyte.decisionengine.service.ExpandFieldService;
import com.vbyte.decisionengine.service.FieldService;
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

