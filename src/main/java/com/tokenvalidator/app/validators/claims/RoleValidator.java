package com.tokenvalidator.app.validators.claims;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import io.jsonwebtoken.Claims;

public class RoleValidator implements ClaimsValidator {
    @Override
    public void validate(Claims claims) throws InvalidClaimsException {
        String role = claims.get("Role", String.class);
        if (!(role.equals("Admin") || role.equals("Member") || role.equals("External"))) {
            throw new InvalidClaimsException("Claim Role contem um valor invalido", "E-0005");
        }
    }
}
