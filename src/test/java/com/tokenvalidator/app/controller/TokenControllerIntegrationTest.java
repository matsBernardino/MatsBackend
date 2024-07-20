package com.tokenvalidator.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokenvalidator.app.model.Error;
import com.tokenvalidator.app.model.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testValidToken() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        mvcCall(validToken, status().isOk(), "Token valido!");
    }

    @Test
    public void testInvalidToken() throws Exception {
        Token validToken = new Token("eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        Error error = new Error("E-9999", "Nao foi possivel processar a solicitacao");

        mvcCall(validToken, status().isUnprocessableEntity(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testNullToken() throws Exception {
        Token validToken = new Token(null);
        Error error = new Error("E-9999", "Nao foi possivel processar a solicitacao");

        mvcCall(validToken, status().isUnprocessableEntity(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testEmptyToken() throws Exception {
        Token validToken = new Token("");
        Error error = new Error("E-0001", "Token nao possui 3 partes");

        mvcCall(validToken, status().isBadRequest(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testMoreCharsThanExpectedName() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyBhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2Fhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2Fhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNhc2FzYXNzUyJ9.fVj8gv2SL3G9LLQ4iXkwyArDlOcprb3oB8qORs4_osw");
        Error error = new Error("E-0007", "Token invalido");

        mvcCall(validToken, status().isUnauthorized(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testSeedNotPrimeToken() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiMTAwIiwiTmFtZSI6IlRvbmluaG8gQXJhdWpvIn0.rH6lg5IetcquRzxpfRlg4n6P0jf2pRWJDeWBCkT3FzE");
        Error error = new Error("E-0006", "Token invalido");

        mvcCall(validToken, status().isUnauthorized(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testRoleNotValidToken() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiVGVzdCIsIlNlZWQiOiI3ODQxIiwiTmFtZSI6IlRvbmluaG8gQXJhdWpvIn0.U_5O-1RqFBUmd6QJ8zOPZc1UMMEc2xrlYo14x81d7Ko");
        Error error = new Error("E-0005", "Token invalido");

        mvcCall(validToken, status().isUnauthorized(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testClaimNameWithNumber() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs");
        Error error = new Error("E-0004", "Token invalido");

        mvcCall(validToken, status().isUnauthorized(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testMoreClaimThanExpected() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY");
        Error error = new Error("E-0003", "Token invalido");

        mvcCall(validToken, status().isUnauthorized(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testInvalidCharToken() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ#1Q01jWY_ZzIZuAg");
        Error error = new Error("E-0002", "Token possui caracteres invalidos");

        mvcCall(validToken, status().isBadRequest(), objectMapper.writeValueAsString(error));
    }

    @Test
    public void testMoreDotsThanExpectedToken() throws Exception {
        Token validToken = new Token("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ.1Q01jWY_ZzIZuAg");
        Error error = new Error("E-0001", "Token nao possui 3 partes");

        mvcCall(validToken, status().isBadRequest(), objectMapper.writeValueAsString(error));
    }

    private void mvcCall(Token validToken, ResultMatcher resultMatcher, String bodyExpected) throws Exception {
        mockMvc.perform(post("/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(validToken)))
                .andDo(print())
                .andExpect(resultMatcher)
                .andExpect(content().string(containsString(bodyExpected)));
    }
}
