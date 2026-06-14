package com.stapimex.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String PATTERN =
            "dd/MM/yyyy";

    private DateUtil() {
    }

    public static String format(Date date) {

        if (date == null) {
            return "";
        }

        return new SimpleDateFormat(
                PATTERN
        ).format(date);
    }
}
