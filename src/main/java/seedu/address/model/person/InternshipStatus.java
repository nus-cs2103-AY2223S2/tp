package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public enum InternshipStatus {
    NA,
    PENDING,
    RECEIVED,
    REJECTED,
    NO;

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be of one of the following five types: NA, PENDING, RECEIVED, REJECTED or NO";

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
