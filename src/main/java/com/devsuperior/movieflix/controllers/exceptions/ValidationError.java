package com.devsuperior.movieflix.controllers.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;
	private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError() {
    }

    public List<FieldMessage> getFieldMessages() {
        return Collections.unmodifiableList(fieldMessages);
    }

    public void addError(String fieldName, String message) {
        fieldMessages.add(new FieldMessage(fieldName, message));
    }
}
