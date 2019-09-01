package com.canteen.app.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilEqualTest {

    private static final String STRING = "example string";

    @Test
    void shouldReturnTrueWhenProvidedTheSameString() {
        boolean result = StringUtil.equal(STRING, STRING);

        assertThat(result).isTrue();
    }

    @Test
    void shouldResultFalseWhenEitherOfStringsOrBothAreNull() {
        assertThat(StringUtil.equal(null, STRING)).isFalse();
        assertThat(StringUtil.equal(STRING, null)).isFalse();
        assertThat(StringUtil.equal(null, null)).isFalse();
    }

    @Test
    void shouldReturnFalseIfThatStringsDiffer() {
        boolean result = StringUtil.equal(STRING, STRING.concat(STRING));

        assertThat(result).isFalse();
    }
}