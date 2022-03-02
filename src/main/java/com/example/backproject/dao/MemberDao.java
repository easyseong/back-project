package com.example.backproject.dao;


import com.example.backproject.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberDao { //DAO, 데이터에 직접적으로 접근

    public List<Member> selectList();  //이 메소드명==xml의 id명

    public List<Member> selectEmail(String email);

    public Member readMember(String email);

    public void create(Member user);


}
