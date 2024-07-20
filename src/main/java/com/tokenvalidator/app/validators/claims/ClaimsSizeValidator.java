package com.tokenvalidator.app.validators.claims;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import io.jsonwebtoken.Claims;

public class ClaimsSizeValidator implements ClaimsValidator {
    @Override
    public void validate(Claims claims) throws InvalidClaimsException {
        if (claims.size() != 3) {
            throw new InvalidClaimsException("Numero incorreto de claims", "E-0003");
        }
    }
}
