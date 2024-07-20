package com.tokenvalidator.app.exceptions;

import lombok.Getter;

public class InvalidClaimsException extends RuntimeException {

    @Getter
    private final String code;

    public InvalidClaimsException(String code) {
        super("Claim invalido");
        this.code = code;
    }

    public InvalidClaimsException(String message,String code) {
        super(message);
        this.code = code;
    }
}
