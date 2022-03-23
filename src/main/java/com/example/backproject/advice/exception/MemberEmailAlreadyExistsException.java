package com.example.backproject.advice.exception;


public class MemberEmailAlreadyExistsException extends RuntimeException {
    public MemberEmailAlreadyExistsException() {
        //중복된아이디로 회원가입
    }

    public MemberEmailAlreadyExistsException(String message) {
        super(message);
    }

    public MemberEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
