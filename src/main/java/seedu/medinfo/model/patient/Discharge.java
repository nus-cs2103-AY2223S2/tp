package seedu.medinfo.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Patient's discharge date in MedInfo.
 */
public class Discharge {

    public static final String MESSAGE_CONSTRAINTS = "Discharge date-time should be a valid future date-time"
            + " of the format dd/MM/yyyy HHmm";
    public static final String DEFAULT_DISCHARGE = "To Be Confirmed";
    public static final String DATE_FORMAT = "dd/MM/yyyy HHmm";

    public final String value;

    /**
     * Constructs a {@code Discharge}.
     *
     * @param discharge A valid discharge date.
     */
    public Discharge(String discharge) {
        requireNonNull(discharge);
        checkArgument(isValidDischarge(discharge), MESSAGE_CONSTRAINTS);
        value = discharge;
    }

    /**
     * Returns true if a given discharge date-time is valid.
     * @param date Date to check.
     */
    public static boolean isValidDischarge(String date) {
        if (date.equals(DEFAULT_DISCHARGE)) {
            return true;
        }
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Returns the dateTime.
     * @return LocalDateTime
     */
    public Date getDateTime() {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            return df.parse(value);
        } catch (ParseException e) {
            return new Date();
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Discharge
                && value.equals(((Discharge) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

