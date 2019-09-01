package com.canteen.app.service.validation.regex;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class PasswordValidator implements RegexValidator {

    @Override
    public String getPattern() {
        //language=RegExp
        return "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    }
}
