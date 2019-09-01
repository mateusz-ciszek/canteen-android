package com.canteen.app.service.validation.regex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = PasswordValidator.of();
    }

    @Test
    void shouldFailWhenShorterThen8Character() {
        boolean result = validator.isValid("far23GA");

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource("getSpecialCharacters")
    void shouldFailWhenContainSpecialCharacter(final char special) {
        boolean result = validator.isValid("1vb4Gbwg6" + special);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123445678f", "adgsthsbGSTB", "1235STGEB", "123456789", "asdargstbat", "ABTSBSNRGAB"})
    void shouldFailWhenMissingAtLeastOneOfRequiredCharacters(final String password) {
        boolean result = validator.isValid(password);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Z234567a", "123a5G56789", "1vb4Gbwg6", "F4Afh75AGv4ue7yjwSCRe5hBrgag"})
    void shouldSucceedWithPasswordLongerThan8CharacterWithAtLeast1NumberSmallCharacterAndBigCharacter(final String password) {
        boolean result = validator.isValid(password);

        assertThat(result).isTrue();
    }

    private static Stream<Character> getSpecialCharacters() {
        return SpecialCharactersProvider.get();
    }
}