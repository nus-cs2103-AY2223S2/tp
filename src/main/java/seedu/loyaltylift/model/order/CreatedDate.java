package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Order's created date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCreatedDate(String)}
 */
public class CreatedDate {

    public static final String MESSAGE_CONSTRAINTS = "CreatedDate can be any date not in the future";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public final LocalDate value;

    /**
     * Constructs a {@code CreatedDate}.
     *
     * @param address A valid date.
     */
    public CreatedDate(LocalDate date) {
        requireNonNull(date);
        checkArgument(isValidCreatedDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid created date.
     */
    public static boolean isValidCreatedDate(LocalDate test) {
        LocalDate now = LocalDate.now();
        return test.isEqual(now) || test.isBefore(now);
    }

    @Override
    public String toString() {
        return value.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreatedDate // instanceof handles nulls
                && value.equals(((CreatedDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
