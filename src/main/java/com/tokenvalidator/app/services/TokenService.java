package com.tokenvalidator.app.services;

import com.tokenvalidator.app.model.Token;
import com.tokenvalidator.app.validators.claims.ClaimsValidator;
import com.tokenvalidator.app.validators.token.TokenValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    private final List<ClaimsValidator> claimsValidators;
    private final List<TokenValidator> tokenValidators;

    public TokenService(List<ClaimsValidator> claimsValidators, List<TokenValidator> tokenValidators) {
        this.claimsValidators = claimsValidators;
        this.tokenValidators = tokenValidators;
    }

    public void validaEstruturaToken(Token token) {
        for (TokenValidator validator : tokenValidators) {
            validator.validate(token);
        }
    }

    public void validaClaims(Token token) {
        Claims claims = decodeToken(token.getValue());

        for (ClaimsValidator validator : claimsValidators) {
            validator.validate(claims);
        }
    }

    public Claims decodeToken(String token) {
        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i + 1);
        return (Claims) Jwts.parser().parse(withoutSignature).getBody();
    }

}
