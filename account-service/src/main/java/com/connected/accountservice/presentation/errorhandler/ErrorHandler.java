package com.connected.accountservice.presentation.errorhandler;

import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.presentation.errorhandler.error.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ErrorHandler {

    private final MessageSource messageSource;

    @Autowired
    public ErrorHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage processValidationError(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        final ArgumentInvalidExceptionProcessor argumentInvalidProcessor =
                ArgumentInvalidExceptionProcessor.of(methodArgumentNotValidException);

        final List<String> errors = argumentInvalidProcessor.processesToErrorList();

        return ErrorMessage.of(errors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ErrorMessage businessError(final BusinessException businessException) {
        final var message = messageSource.getMessage(businessException.getMessage(),
                null, Locale.getDefault());

        return ErrorMessage.of(message);
    }
}