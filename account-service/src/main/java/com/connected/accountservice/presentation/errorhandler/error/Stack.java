package com.connected.accountservice.presentation.errorhandler.error;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Stack {

    private final List<String> errors;

    private Stack(final List<String> errors) {
        this.errors = errors;
    }

    static Stack of(final List<String> errors) {
        return new Stack(errors);
    }

    static Stack of(final String error) {
        return new Stack(List.of(error));
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errors", errors)
                .toString();
    }
}
