package seedu.address.model.person;

/**
 * Represents the possible statuses for an internship application.
 */
public enum InternshipStatus {
    PENDING("Pending reply", "#FDA50F"),
    RECEIVED("Offer received", "#89CFF0"),
    ACCEPTED("Offer accepted", "#32CD32"),
    REJECTED("Offer rejected", "#808080"),
    NO("Application rejected", "#D40E2F"),
    ARCHIVED("Application archived", "#808080");

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be of one of the following five types: PENDING, RECEIVED, ACCEPTED, REJECTED or NO";

    public final String label;
    public final String labelColour;

    private InternshipStatus(String label, String labelColour) {
        this.label = label;
        this.labelColour = labelColour;
    }

    /**
     * Checks whether the String passed is a valid application status
     * @param test String to check
     * @return Boolean indicating whether the String is a valid status
     */
    public static boolean isValidStatus(String test) {
        InternshipStatus[] internshipStatuses = InternshipStatus.values();
        for (InternshipStatus internshipStatus : internshipStatuses) {
            if (internshipStatus.name().equals(test.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
