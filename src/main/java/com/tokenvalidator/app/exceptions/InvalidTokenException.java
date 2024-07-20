package com.tokenvalidator.app.exceptions;

import lombok.Getter;

public class InvalidTokenException extends RuntimeException {

    @Getter
    private final String code;

    public InvalidTokenException(String code) {
        super("Token JWT invalido");
        this.code = code;
    }

    public InvalidTokenException(String message,String code) {
        super(message);
        this.code = code;
    }

}
