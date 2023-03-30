package seedu.medinfo.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Patient's discharge date in MedInfo.
 */
public class Discharge {

    public static final String MESSAGE_CONSTRAINTS = "Discharge date-time should be of the form dd/MM/yyyy HHmm";
    public static final String DEFAULT_DISCHARGE = "To Be Confirmed";

    public final String value;

    /**
     * Constructs a {@code Discharge}.
     *
     * @param discharge A valid discharge.
     */
    public Discharge(String discharge) {
        requireNonNull(discharge);
        checkArgument(isValidDischarge(discharge), MESSAGE_CONSTRAINTS);
        value = discharge;
    }

    /**
     * Returns true if a given discharge date-time is valid.
     */
    public static boolean isValidDischarge(String discharge) {
        if (discharge.equals(DEFAULT_DISCHARGE)) {
            return true;
        }

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(discharge, format);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public LocalDateTime getDateTime() {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(value, format);
        } catch (DateTimeParseException e) {
            return LocalDateTime.MIN;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Discharge // instanceof handles nulls
                && value.equals(((Discharge) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

