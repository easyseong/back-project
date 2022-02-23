package com.example.backproject.dao;

import com.example.backproject.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SignDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("mssqlSqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    /* test 조회 */
    public User getUser(Object param) {
        User user = sqlSession.selectOne("v1.getUser", param);
        return user;
    }
}

