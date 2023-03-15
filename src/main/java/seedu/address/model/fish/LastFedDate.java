package seedu.address.model.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.model.date.DateUtil;

/**
 * Represents a Fish's last fed date number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLastFedDate(String)}
 */
public class LastFedDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Last Fed Date is a date time in the format of dd/mm/yyyy";
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
    public final String value;
    public final LocalDate localDate;

    /**
     * Constructs a {@code LastFedDate}.
     *
     * @param lastFedDate A valid last Fed Date number.
     */
    public LastFedDate(String lastFedDate) {
        requireNonNull(lastFedDate);
        checkArgument(isValidLastFedDate(lastFedDate), MESSAGE_CONSTRAINTS);
        value = lastFedDate;
        localDate = DateUtil.parseStringToDate(lastFedDate);
    }

    /**
     * Returns true if a given string is a valid last Fed Date number.
     */
    public static boolean isValidLastFedDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastFedDate // instanceof handles nulls
                && localDate.equals(((LastFedDate) other).localDate)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
