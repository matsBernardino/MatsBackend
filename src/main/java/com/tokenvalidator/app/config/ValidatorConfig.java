package com.tokenvalidator.app.config;

import com.tokenvalidator.app.validators.claims.ClaimsSizeValidator;
import com.tokenvalidator.app.validators.claims.ClaimsValidator;
import com.tokenvalidator.app.validators.claims.NameValidator;
import com.tokenvalidator.app.validators.claims.RoleValidator;
import com.tokenvalidator.app.validators.claims.SeedValidator;
import com.tokenvalidator.app.validators.token.TokenInvalidCharValidator;
import com.tokenvalidator.app.validators.token.TokenPartsValidator;
import com.tokenvalidator.app.validators.token.TokenValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ValidatorConfig {

    @Bean
    public List<TokenValidator> tokenValidators() {
        return Arrays.asList(
                new TokenInvalidCharValidator(),
                new TokenPartsValidator()
        );
    }

    @Bean
    public List<ClaimsValidator> claimsValidators() {
        return Arrays.asList(
                new ClaimsSizeValidator(),
                new NameValidator(),
                new RoleValidator(),
                new SeedValidator()
        );
    }
}
