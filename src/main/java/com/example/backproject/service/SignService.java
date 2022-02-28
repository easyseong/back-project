package com.example.backproject.service;

import com.example.backproject.model.Member;

import java.util.List;

public interface SignService {

    public List<Member> selectList();

    public List<Member> selectEmail(String email);

    public Member readMember(String email);

    public void create(Member user);
}
