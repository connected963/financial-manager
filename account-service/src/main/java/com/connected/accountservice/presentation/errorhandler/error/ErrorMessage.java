package com.connected.accountservice.presentation.errorhandler.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorMessage {

    private final Stack stack;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime date;

    private ErrorMessage(final Stack stack) {
        this.stack = stack;
        this.date = LocalDateTime.now();
    }

    public static ErrorMessage of(final String error) {
        final Stack stack = Stack.of(error);

        return new ErrorMessage(stack);
    }

    public static ErrorMessage of(final List<String> errors) {
        final Stack stack = Stack.of(errors);

        return new ErrorMessage(stack);
    }

    public Stack getStack() {
        return stack;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "stack=" + stack +
                ", date=" + date +
                '}';
    }
}
