package com.canteen.app.service.validation;

public interface Validator<T> {
    boolean isValid(final T value);
}
