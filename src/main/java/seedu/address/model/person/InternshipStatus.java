package seedu.address.model.person;

/**
 * Represents the possible statuses for an internship application.
 */
public enum InternshipStatus {
    NA,
    PENDING,
    RECEIVED,
    REJECTED,
    NO;

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be of one of the following five types: NA, PENDING, RECEIVED, REJECTED or NO";

    /**
     * Checks whether the String passed is a valid application status
     * @param test String to check
     * @return Boolean indicating whether the String is a valid status
     */
    public static boolean isValidStatus(String test) {
        InternshipStatus[] internshipStatuses = InternshipStatus.values();
        for (InternshipStatus internshipStatus : internshipStatuses) {
            if (internshipStatus.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
