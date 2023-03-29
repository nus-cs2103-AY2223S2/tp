package seedu.address.model.tank.equipment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.model.date.DateUtil;

/**
 * Represents an Equipment's last maintained date number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLastMaintainedDate(String)}
 */
public class LastMaintainedDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Last Maintained Date is a date time in the format of dd/mm/yyyy";
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
    public final String value;
    public final LocalDate localDate;
    public final String alphaNumericDate;

    /**
     * Constructs a {@code LastMaintainedDate}.
     *
     * @param lastMaintainedDate A valid last Maintained Date number.
     */
    public LastMaintainedDate(String lastMaintainedDate) {
        requireNonNull(lastMaintainedDate);
        checkArgument(isValidLastMaintainedDate(lastMaintainedDate), MESSAGE_CONSTRAINTS);
        value = lastMaintainedDate;
        localDate = DateUtil.parseStringToDate(lastMaintainedDate);
        alphaNumericDate = DateUtil.getTaskDescriptionDateFormat(localDate);
    }

    /**
     * Returns true if a given string is a valid last Maintained Date number.
     */
    public static boolean isValidLastMaintainedDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String getAlphaNumericDate() {
        return this.alphaNumericDate;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastMaintainedDate // instanceof handles nulls
                && localDate.equals(((LastMaintainedDate) other).localDate)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
