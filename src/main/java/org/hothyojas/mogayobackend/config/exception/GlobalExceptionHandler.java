package org.hothyojas.mogayobackend.config.exception;

import java.util.NoSuchElementException;
import org.hothyojas.mogayobackend.dtos.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<?> handleNoSuchElementException(NoSuchElementException e) {
        return new BaseResponse<>(e);
    }
}
