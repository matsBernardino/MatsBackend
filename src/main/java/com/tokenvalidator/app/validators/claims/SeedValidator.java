package com.tokenvalidator.app.validators.claims;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import io.jsonwebtoken.Claims;

public class SeedValidator implements ClaimsValidator {
    @Override
    public void validate(Claims claims) throws InvalidClaimsException {
        int seed = Integer.parseInt(claims.get("Seed", String.class));
        if (!isPrime(seed)) {
            throw new InvalidClaimsException("Claim Seed nao eh um numero primo", "E-0006");
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
