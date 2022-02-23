package com.example.backproject.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MssqlRestDao {

    @Autowired
    @Qualifier("mssqlSqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    /* test 조회 */
    public List<Map> selectList(Object param) {
        return sqlSession.selectList("com.tbUser.select1", param);
    }


}
