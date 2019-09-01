package com.canteen.app.service.validation.regex;

import java.util.stream.Stream;

class SpecialCharactersProvider {

    private SpecialCharactersProvider() { }

    static Stream<Character> get() {
        return Stream.of('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', ' ', '-', '_', '+', '=', ',', '.', '<', '>', ';', ':', '\\',
                '|', '{', '[', '}', ']', '"', '\'', '/', '?', '~', '`');
    }
}
