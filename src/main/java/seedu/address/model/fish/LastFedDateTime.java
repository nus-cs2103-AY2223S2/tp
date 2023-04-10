package seedu.address.model.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.date.DateUtil;

/**
 * Represents a Fish's last fed date time number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLastFedDateTime(String)}
 */
public class LastFedDateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Last Fed Date Time is a date time in the format of \"dd/MM/yyyy HH:mm\"";
    public static final String VALIDATION_REGEX =
            "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} (?:[01]\\d|2[0-3]):[0-5]\\d$";

    public final String value;
    public final LocalDateTime localDateTime;
    public final String alphaNumericDateTime;

    /**
     * Constructs a {@code LastFedDate}.
     *
     * @param lastFedDateTime A valid last Fed Date number.
     */
    public LastFedDateTime(String lastFedDateTime) {
        requireNonNull(lastFedDateTime);
        checkArgument(isValidLastFedDateTime(lastFedDateTime), MESSAGE_CONSTRAINTS);

        localDateTime = DateUtil.parseStringToDateTime(lastFedDateTime);
        value = DateUtil.convertDateTimeToString(localDateTime);
        alphaNumericDateTime = DateUtil.getTaskDescriptionDateTimeFormat(localDateTime);
    }

    /**
     * Returns true if a given string is a valid last Fed Date number.
     */
    public static boolean isValidLastFedDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String getAlphaNumericDateTime() {
        return this.alphaNumericDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastFedDateTime // instanceof handles nulls
                && localDateTime.equals(((LastFedDateTime) other).localDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
