package com.tokenvalidator.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Error {
    @Getter @Setter
    String code;
    @Getter @Setter
    String message;
}
