package com.canteen.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilIsNullOrEmptyTest {

    @Test
    void shouldReturnTrueWhenProvidedNull() {
        boolean result = StringUtil.isNullOrEmpty(null);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueWhenProvidedEmptyString() {
        boolean result = StringUtil.isNullOrEmpty("");

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", " ", "lorem ipsum dolor sit amet"})
    void shouldReturnFalseWhenProvidedNotEmptyString(final String value) {
        boolean result = StringUtil.isNullOrEmpty(value);

        assertThat(result).isFalse();
    }
}