package com.tokenvalidator.app.service;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import com.tokenvalidator.app.exceptions.InvalidTokenException;
import com.tokenvalidator.app.model.Token;
import com.tokenvalidator.app.services.TokenService;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TokenServiceTests {

    @Autowired
    private TokenService tokenService;

    @Test
    void testValidToken() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        assertDoesNotThrow(() -> tokenService.validaEstruturaToken(validToken));
        assertDoesNotThrow(() -> tokenService.validaClaims(validToken));
    }

    @Test
    void testMoreDotsThanExpectedToken() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbW.UiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");

        InvalidTokenException exception = assertThrows(InvalidTokenException.class, () -> tokenService.validaEstruturaToken(validToken));

        assertEquals(exception.getCode(), "E-0001");
        assertEquals(exception.getMessage(), "Token nao possui 3 partes");
    }

    @Test
    void testInvalidCharToken() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbW UiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");

        InvalidTokenException exception = assertThrows(InvalidTokenException.class, () -> tokenService.validaEstruturaToken(validToken));

        assertEquals(exception.getCode(), "E-0002");
        assertEquals(exception.getMessage(), "Token possui caracteres invalidos");

    }

    @Test
    void testInvalidToken() {
        Token validToken = new Token("eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");

        MalformedJwtException exception = assertThrows(MalformedJwtException.class, () -> tokenService.validaClaims(validToken));

        assertEquals(exception.getMessage(), "Unable to read JSON value: {\"alg\":\"s#Sb'");
    }

    @Test
    void testClaimNameWithNumber() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs");
        InvalidClaimsException exception = assertThrows(InvalidClaimsException.class, () -> tokenService.validaClaims(validToken));

        assertEquals(exception.getCode(), "E-0004");
        assertEquals(exception.getMessage(), "Claim Name contem caracteres numericos");
    }

    @Test
    void testMoreClaimThanExpected() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY");
        InvalidClaimsException exception = assertThrows(InvalidClaimsException.class, () -> tokenService.validaClaims(validToken));

        assertEquals(exception.getCode(), "E-0003");
        assertEquals(exception.getMessage(), "Numero incorreto de claims");
    }

    @Test
    void testMoreCharsThanExpectedName() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyBhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2Fhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2Fhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNzUyJ9.fVj8gv2SL3G9LLQ4iXkwyArDlOcprb3oB8qORs4_osw");
        InvalidClaimsException exception = assertThrows(InvalidClaimsException.class, () -> tokenService.validaClaims(validToken));

        assertEquals(exception.getCode(), "E-0007");
        assertEquals(exception.getMessage(), "Claim Name excede 256 caracteres");
    }

    @Test
    void testSeedNotPrimeToken() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiMTAwIiwiTmFtZSI6IlRvbmluaG8gQXJhdWpvIn0.rH6lg5IetcquRzxpfRlg4n6P0jf2pRWJDeWBCkT3FzE");

        InvalidClaimsException exception = assertThrows(InvalidClaimsException.class, () -> tokenService.validaClaims(validToken));

        assertEquals(exception.getCode(), "E-0006");
        assertEquals(exception.getMessage(), "Claim Seed nao eh um numero primo");
    }

    @Test
    void testRoleNotValidToken() {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiVGVzdCIsIlNlZWQiOiI3ODQxIiwiTmFtZSI6IlRvbmluaG8gQXJhdWpvIn0.U_5O-1RqFBUmd6QJ8zOPZc1UMMEc2xrlYo14x81d7Ko");

        InvalidClaimsException exception = assertThrows(InvalidClaimsException.class, () -> tokenService.validaClaims(validToken));

        assertEquals(exception.getCode(), "E-0005");
        assertEquals(exception.getMessage(), "Claim Role contem um valor invalido");
    }


}
