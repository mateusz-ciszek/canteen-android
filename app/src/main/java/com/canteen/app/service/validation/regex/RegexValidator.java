package com.canteen.app.service.validation.regex;

import com.canteen.app.service.StringUtil;
import com.canteen.app.service.validation.Validator;

import java.util.regex.Pattern;

public interface RegexValidator extends Validator<String> {

    String getPattern();

    @Override
    default boolean isValid(final String value) {
        if (StringUtil.isNullOrEmpty(getPattern()) || StringUtil.isNullOrEmpty(value)) {
            return false;
        }

        return Pattern.matches(getPattern(), value);
    }
}
