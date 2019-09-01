package com.canteen.app.service;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    public static boolean equal(final String value1, final String value2) {
        return !isNullOrEmpty(value1) && value1.equals(value2);
    }
}
