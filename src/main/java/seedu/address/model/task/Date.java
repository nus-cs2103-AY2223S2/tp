package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * Represents a Date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should only contain numeric characters and be in the format dd/mm/yyyy ,"
                    + " and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";

    public final String fullDate;
    public final LocalDate localDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        localDate = LocalDate.parse(date, formatter);
        fullDate = date;
    }

    /**
     * Returns true if a given dateis a valid name.
     *
     * @param test
     * @return true if a given date is a valid name
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX) && validateJavaDate(test);
    }

    /**
     * Return true is given string is a valid date.
     *
     * @param dateStr
     * @return true is given date is valid.
     */
    public static boolean validateJavaDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Return True if given date is the same day or after current date.
     *
     * @param otherDate
     * @return true if given Date is on or after current date.
     */
    public boolean isBefore(Date otherDate) {
        return this.localDate.isBefore(otherDate.getLocalDate());
    }

    /**
     * Supplies LocalDate representation of the current date when requested.
     *
     * @return LocalDate representation of the Date
     */
    public LocalDate getLocalDate() {
        return this.localDate;
    }

    @Override
    public String toString() {
        return fullDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.Date // instanceof handles nulls
                && fullDate.equals(((seedu.address.model.task.Date) other).fullDate)); // state check
    }

    @Override
    public int hashCode() {
        return fullDate.hashCode();
    }

}
