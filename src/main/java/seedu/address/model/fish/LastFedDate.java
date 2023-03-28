package seedu.address.model.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.date.DateUtil;

/**
 * Represents a Fish's last fed date number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLastFedDate(String)}
 */
public class LastFedDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Last Fed Date Time is a date time in the format of \"dd/MM/yyyy HH:mm:ss\"";
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}\\s"
            + "([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$\n";
    public final String value;
    public final LocalDateTime localDateTime;
    public final String alphaNumericDateTime;

    /**
     * Constructs a {@code LastFedDate}.
     *
     * @param lastFedDateTime A valid last Fed Date number.
     */
    public LastFedDate(String lastFedDateTime) {
        requireNonNull(lastFedDateTime);
        checkArgument(isValidLastFedDate(lastFedDateTime), MESSAGE_CONSTRAINTS);
        value = lastFedDateTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        localDateTime = LocalDateTime.parse(lastFedDateTime, formatter);

        alphaNumericDateTime = DateUtil.getTaskDescriptionDateTimeFormat(localDateTime);

    }

    /**
     * Returns true if a given string is a valid last Fed Date number.
     */
    public static boolean isValidLastFedDate(String test) {
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
                || (other instanceof LastFedDate // instanceof handles nulls
                && localDateTime.equals(((LastFedDate) other).localDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
