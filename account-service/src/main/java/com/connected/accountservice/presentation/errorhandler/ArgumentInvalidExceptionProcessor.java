package com.connected.accountservice.presentation.errorhandler;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

class ArgumentInvalidExceptionProcessor {

    private final MethodArgumentNotValidException methodArgumentNotValidException;

    private ArgumentInvalidExceptionProcessor(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        this.methodArgumentNotValidException = methodArgumentNotValidException;
    }

    static ArgumentInvalidExceptionProcessor of(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ArgumentInvalidExceptionProcessor(methodArgumentNotValidException);
    }

    List<String> processesToErrorList() {
        final BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return buildErrorList(fieldErrors);
    }

    private List<String> buildErrorList(final List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
