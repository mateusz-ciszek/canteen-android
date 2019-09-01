package com.canteen.app.service.validation.regex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class RegexValidatorTest {

    private static final String EXPRESSION = ".*";

    private RegexValidator validator;

    @Test
    void shouldNotThrowExceptionWithNull() {
        validator = () -> EXPRESSION;

        assertThatCode(() -> validator.isValid(null)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotThrowExceptionWhenPatternIsNull() {
        validator = () -> null;

        assertThatCode(() -> validator.isValid("asd")).doesNotThrowAnyException();
    }
}