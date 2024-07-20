package com.tokenvalidator.app.validators.token;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import com.tokenvalidator.app.exceptions.InvalidTokenException;
import com.tokenvalidator.app.model.Token;

public class TokenPartsValidator implements TokenValidator {
    @Override
    public void validate(Token token) throws InvalidClaimsException {
        String[] parts = token.getValue().split("\\.");
        if (parts.length != 3) {
            throw new InvalidTokenException("Token nao possui 3 partes", "E-0001");
        }
    }
}

