package com.tokenvalidator.app.controller;

import com.tokenvalidator.app.model.Token;
import com.tokenvalidator.app.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/validate")
    public ResponseEntity<String> validate(@RequestBody Token token) {

//      Valida estrutura do token
        tokenService.validaEstruturaToken(token);

//      Valida os claims
        tokenService.validaClaims(token);

        return ResponseEntity.ok("Token valido!");
    }


}