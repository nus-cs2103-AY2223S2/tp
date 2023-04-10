package seedu.address.model.person;

/**
 * Represents an Applicant's status
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public enum Status {
    APPLIED,
    SHORTLISTED,
    ACCEPTED,
    REJECTED;

    public static final String MESSAGE_CONSTRAINTS = "Status can only be APPLIED, SHORTLISTED, ACCEPTED, REJECTED.";

    /**
     * Returns if a given string is a valid status.
     * @param value status string to be tested.
     * @return boolean value of whether the string is a valid status.
     */
    public static boolean isValidStatus(String value) {
        for (Status status : Status.values()) {
            if (value.equals(status.name())) {
                return true;
            }
        }
        return false;
    }
}
