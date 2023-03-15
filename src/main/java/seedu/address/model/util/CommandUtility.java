package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class which contains helper functions related to various commands, such as conversion of date and times
 */
public class CommandUtility {

    /**
     * Parses a date string input in any allowed format and returns a LocalDate object.
     * @param input A date string of any allowed format
     * @return A LocalDate object representing the parsed date
     * @throws IllegalArgumentException If the input string cannot be parsed into a valid date
     */
    public static LocalDate parseDateFromUserInput(String input) throws IllegalArgumentException {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("dd/MM/yy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d/MM/yy"),
                DateTimeFormatter.ofPattern("d/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd/M/yy"),
                DateTimeFormatter.ofPattern("dd/M/yyyy")
        };
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                continue;
            }
        }
        throw new IllegalArgumentException("Invalid date format");
    }

}

