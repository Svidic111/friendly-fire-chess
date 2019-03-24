package com.ffirechess.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 3423042923925938727L;

    public UserServiceException(String message) {
        super(message);
    }
}
