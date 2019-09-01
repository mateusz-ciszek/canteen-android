package com.canteen.app.service.validation.regex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailValidatorTest {

    private static final String VALID_EMAIL = "user@test.com";

    private EmailValidator validator;

    @BeforeEach
    void setUp() {
        validator = EmailValidator.of();
    }

    @Test
    void shouldSucceedWithValidEmail() {
        boolean result = validator.isValid(VALID_EMAIL);

        assertThat(result).isTrue();
    }

    @Test
    void shouldFailWithoutAtSign() {
        boolean result = validator.isValid(VALID_EMAIL.replace("@", ""));

        assertThat(result).isFalse();
    }

}