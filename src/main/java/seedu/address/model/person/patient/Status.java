package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Patient's hospitalization status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should be one of the following: Inpatient, Outpatient, Observation, Emergency Department,"
                    + " Intensive Care Unit, Transitional Care";

    public static final String VALIDATION_REGEX = "(?i)^(Inpatient|Outpatient|Observation|Emergency Department"
            + "|Intensive Care Unit|Transitional Care)$";

    private final String status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid hospitalization status.
     */
    public Status(String status) {
        requireNonNull(status);
        this.status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid hospitalization status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && status.equalsIgnoreCase(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.toLowerCase().hashCode();
    }
}
