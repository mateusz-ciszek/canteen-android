package com.canteen.app.service.validation.regex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NameValidatorTest {

    private NameValidator validator;

    @BeforeEach
    void setUp() {
        validator = NameValidator.of();
    }

    @Test
    void shouldFailWithoutBigCharacterAtTheBeginning() {
        boolean result = validator.isValid("romeo");

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ROMEO", "ROmeo", "RomeO", "RoMEO"})
    void shouldFailWithMoreThanOneBigCharacter(final String name) {
        boolean result = validator.isValid(name);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "r"})
    void ShouldFailWhenShorterThanTwoCharacters(final String name) {
        boolean result = validator.isValid(name);

        assertThat(result).isFalse();
    }

    @Test
    void shouldSucceedWhenLongerThanTwoCharactersAndWithOnlyOneBigCharacterAtTheBeginning() {
        boolean result = validator.isValid("Juliet");

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getSpecialCharacters")
    void shouldFailWithSpecialCharacter(final char special) {
        boolean result = validator.isValid("Romeo" + special);

        assertThat(result).isFalse();
    }

    private static Stream<Character> getSpecialCharacters() {
        return SpecialCharactersProvider.get();
    }
}
