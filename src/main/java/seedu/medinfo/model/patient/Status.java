package seedu.medinfo.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient status in the medinfo book.
 */
public class Status {
    /**
     * The colour codes below reflect the severity of a patient's condition
     * and the urgency of treatment needed.
     *
     * GRAY: Unknown condition, waiting for evaluation
     * GREEN: Stable condition, re-evaluation every 180 min
     * YELLOW: Serious condition, re-evaluation every 60 min
     * RED: Critical condition, requires immediate evaluation by physician
     */

    public static final String MESSAGE_CONSTRAINTS = "Statuses should only be 'GRAY', 'GREEN', 'YELLOW', or 'RED'";
    private static String[] values = { "GRAY", "GREEN", "YELLOW", "RED" };

    public final String value;

    /**
     * Constructs an {@code status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        for (String value : values) {
            if (test.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns description of the status code.
     *
     * @return Description of status code.
     */
    public String getDesc() {
        switch(value) {
        case "GREEN":
            return "STABLE";
        case "YELLOW":
            return "SERIOUS";
        case "RED":
            return "CRITICAL";
        default:
            return "UNKNOWN";
        }
    }

    /**
     * Returns the numeric value of each status.
     *
     * @return Numeric value of each status.
     */
    public Integer getValue() {
        switch ((value)) {
        case "GREEN":
            return 1;
        case "YELLOW":
            return 2;
        case "RED":
            return 3;
        default:
            return 0;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
