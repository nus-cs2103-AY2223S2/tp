package seedu.fitbook.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Client's date of weight in FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in dd-mm-yyyy HH:mm format.";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date should have valid date.";
    public static final String VALIDATION_REGEX = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[0-2])-(\\d{4})$";
    public final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final String weightDate;
    public final LocalDate localDate;

    /**
     * Constructs an {@code Appoinment}.
     *
     * @param date A valid date for the input weight.
     */
    public Date(String date) {
        requireNonNull(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        checkArgument(isValidDate(date), MESSAGE_DATE_CONSTRAINTS);
        weightDate = date;
        this.localDate= LocalDate.parse(date, formatter);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        assert date != null : "date is null";
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(date, formatter);
            return date.matches(VALIDATION_REGEX);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return localDate.format(dateTimeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return this.localDate.equals(otherDate.localDate);
    }

    public LocalDate getDate() {
        return this.localDate;
    }

    @Override
    public int hashCode() {
        return weightDate.hashCode();
    }
}
