package com.tokenvalidator.app.validators.token;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import com.tokenvalidator.app.exceptions.InvalidTokenException;
import com.tokenvalidator.app.model.Token;

public class TokenInvalidCharValidator implements TokenValidator {
    @Override
    public void validate(Token token) throws InvalidClaimsException {
        String[] parts = token.getValue().split("\\.");
        for (String part : parts) {
            if (!part.matches("^[\\w\\d+\\-_]*$")) {
                throw new InvalidTokenException("Token possui caracteres invalidos", "E-0002");
            }
        }
    }
}
