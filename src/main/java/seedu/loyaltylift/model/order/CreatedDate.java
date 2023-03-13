package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Order's created date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCreatedDate(String)}
 */
public class CreatedDate {

    public static final String MESSAGE_CONSTRAINTS = "CreatedDate can be any date not in the future";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    public final Date value;

    /**
     * Constructs a {@code CreatedDate}.
     *
     * @param date A valid date.
     */
    public CreatedDate(Date date) {
        requireNonNull(date);
        checkArgument(isValidCreatedDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid created date.
     */
    public static boolean isValidCreatedDate(Date test) {
        Date now = new Date();
        return test.equals(now) || test.before(new Date());
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(value);
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
