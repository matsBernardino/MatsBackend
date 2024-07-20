package com.tokenvalidator.app.validators.claims;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import io.jsonwebtoken.Claims;

public interface ClaimsValidator {
    void validate(Claims claims) throws InvalidClaimsException;
}

