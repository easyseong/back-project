package com.example.backproject.dao;


import com.example.backproject.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    public List<Member> selectList(String email);

    public void create()


}
