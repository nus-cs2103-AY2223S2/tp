package seedu.address.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class which contains helper functions related to various commands, such as conversion of date and times
 */
public class CommandUtility {

    public static Date parseDateFromUserInput(String input) throws IllegalArgumentException {

        SimpleDateFormat[] formats = {
                new SimpleDateFormat("dd/MM/yy"),
                new SimpleDateFormat("dd/MM/yyyy"),
                new SimpleDateFormat("d/M/yy"),
                new SimpleDateFormat("d/M/yyyy"),
                new SimpleDateFormat("d/MM/yy"),
                new SimpleDateFormat("d/MM/yyyy"),
                new SimpleDateFormat("dd/M/yy"),
                new SimpleDateFormat("dd/M/yyyy")
        };
        for (SimpleDateFormat format : formats) {
            try {
                return format.parse(input);
            } catch (java.text.ParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Invalid date format");
    }
}

