package com.tokenvalidator.app.infra;

import com.tokenvalidator.app.exceptions.InvalidClaimsException;
import com.tokenvalidator.app.exceptions.InvalidTokenException;
import com.tokenvalidator.app.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidTokenException.class)
    private Error InvalidTokenHandler(InvalidTokenException exception) {
        logger.error("Exception code: {}, Request Details: {}", exception.getCode(), exception.getMessage(), exception);
        return new Error(exception.getCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler({Exception.class})
    private Error InvalidTokenHandler(Exception exception) {
        logger.error("Exception code: {}, Request Details: {}", "E-9999", exception.getMessage(), exception);
        return new Error("E-9999", "Nao foi possivel processar a solicitacao");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler({InvalidClaimsException.class})
    private Error InvalidClaimsHandler(InvalidClaimsException exception) {
        logger.error("Exception code: {}, Request Details: {}", exception.getCode(), exception.getMessage(), exception);
        return new Error(exception.getCode(), "Token invalido");
    }
}
