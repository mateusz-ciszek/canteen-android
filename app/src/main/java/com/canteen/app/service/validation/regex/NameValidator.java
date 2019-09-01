package com.canteen.app.service.validation.regex;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class NameValidator implements RegexValidator {

    @Override
    public String getPattern() {
        //language=RegExp
        return "^[A-Z][a-z]+$";
    }
}
