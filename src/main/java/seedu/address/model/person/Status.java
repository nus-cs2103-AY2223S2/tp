package seedu.address.model.person;

public enum Status {
    APPLIED,
    SHORTLISTED,
    ACCEPTED,
    REJECTED;

    public static final String MESSAGE_CONSTRAINTS = "Status can only be APPLIED, SHORTLISTED, ACCEPTED, REJECTED.";


    public static boolean isValidStatus(String value) {
        for (Status status : Status.values()) {
            if (value.equals(status.name())) {
                return true;
            }
        }
        return false;
    }
}
