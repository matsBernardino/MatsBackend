package com.tokenvalidator.app.validators.token;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import com.tokenvalidator.app.exceptions.InvalidTokenException;
import com.tokenvalidator.app.model.Token;

public interface TokenValidator {
    void validate(Token token) throws InvalidTokenException, InvalidClaimsException;
}


