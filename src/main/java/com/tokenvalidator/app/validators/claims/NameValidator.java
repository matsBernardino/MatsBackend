package com.tokenvalidator.app.validators.claims;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import io.jsonwebtoken.Claims;

public class NameValidator implements ClaimsValidator {
    @Override
    public void validate(Claims claims) throws InvalidClaimsException {
        String name = claims.get("Name", String.class);
        if (name == null || name.matches(".*\\d.*")) {
            throw new InvalidClaimsException("Claim Name contem caracteres numericos", "E-0004");
        }
        if (name.length() > 256) {
            throw new InvalidClaimsException("Claim Name excede 256 caracteres", "E-0007");
        }
    }
}
