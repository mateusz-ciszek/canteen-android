package com.canteen.app.service.validation.regex;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class EmailValidator implements RegexValidator {

    @Override
    public String getPattern() {
        //language=RegExp
        return "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    }
}
