package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a job's job date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DeliveryDate {

    public static final String MESSAGE_CONSTRAINTS = "Dates must be valid date "
            + "- only contain numeric characters and spaces, "
            + "and it should not be blank.\n"
            + "Date should have format like this: YYYY-mm-DD";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final DateTimeFormatter VALID_FORMAT = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    /**
     * Represents invalid date in storage.
     */
    private static final String PLACEHOLDER = "9999-12-31";

    public final String date;

    /**
     * Constructs a {@code JobDate}.
     *
     * @param date A valid date.
     */
    public DeliveryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Constructs a placeholder {@code JobDate}.
     *
     * @param date A valid date.
     */
    private DeliveryDate() {
        this.date = PLACEHOLDER;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns date in LocalDate format
     */
    public LocalDate getDate() {
        return LocalDate.parse(this.date);
    }

    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryDate // instanceof handles nulls
                        && date.equals(((DeliveryDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public static DeliveryDate placeholder() {
        return new DeliveryDate();
    }

}
