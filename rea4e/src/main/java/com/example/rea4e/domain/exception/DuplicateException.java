package com.example.rea4e.domain.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String msg){
        super(msg);
    }
}
