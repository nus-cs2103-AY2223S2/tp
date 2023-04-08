package seedu.address.model.client.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * A class that represents a date in the format of dd.mm.yyyy.
 */
public class CustomDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should only contain numbers, in the format of dd.mm.yyyy. Be sure of "
                   + "leap years as well!";

    public static final String VALIDATION_REGEX = "^\\d{2}\\.\\d{2}.\\d{4}$";

    public final LocalDate date;

    /**
     * Constructs a {@code Phone}.
     *
     * @param stringDate A valid date.
     */
    public CustomDate(String stringDate) {
        requireNonNull(stringDate);
        checkArgument(isValidDate(stringDate), MESSAGE_CONSTRAINTS);
        this.date = stringToDate(stringDate);
    }

    /**
     * Converts a string into a LocalDate object.
     * String has to be in the format of dd.MM.yyyy.
     *
     * @param date String to be converted into LocalDate object.
     * @return LocalDate object.
     */
    public static LocalDate stringToDate(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.from(sf.parse(date));
    }


    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        boolean valid = true;
        if (!date.matches(VALIDATION_REGEX)) {
            valid = false;
        } else {
            try {
                // Checks for leap years
                String[] dateParts = date.split("\\.");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if ((day == 29 || day == 30 || day == 31) && month == 2 && !Year.of(year).isLeap()) {
                    valid = false;
                }

                LocalDate localDate = stringToDate(date);
                if (localDate == null) {
                    valid = false;
                }
            } catch (DateTimeParseException | NumberFormatException e) {
                valid = false;
            }
        }
        return valid;
    }

    public String getDisplayString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return outputFormat.format(date);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return outputFormat.format(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomDate // instanceof handles nulls
                && date.equals(((CustomDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
