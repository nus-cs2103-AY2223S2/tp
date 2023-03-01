package seedu.address.model.internship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Represents an Internship's status in InternBuddy
 */
public enum Status {
    //Possible values for Status
    NEW,
    APPLIED,
    ASSESSMENT,
    INTERVIEW,
    OFFERED,
    REJECTED;

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be one of the following: new, applied, assessment, interview, offered or rejected. It" +
                    "should not be blank too.";

    //Initialise a set of valid statuses
    public static List<String> listOfValidStatuses =
            Arrays.asList("new", "applied", "assessment", "interview", "offered", "rejected");
    public static final HashSet<String> setOfValidStatuses = new HashSet<String>(listOfValidStatuses);


    /**
     * Returns true if a given string is a valid status
     *
     * @param test The string to check for.
     * @return true if the given string corresponds to a valid string for a role, else returns false.
     */
    public static boolean isValidStatus(String test) {
        return setOfValidStatuses.contains(test);
    }

    /**
     * Returns the String representation of the Role.
     *
     * @return a String representing the role.
     */
    @Override
    public String toString() {
        switch (this) {
        case NEW:
            return "new";

        case APPLIED:
            return "applied";

        case ASSESSMENT:
            return "assessment";

        case INTERVIEW:
            return "interview";

        case OFFERED:
            return "offered";

        case REJECTED:
            return "rejected";

        default:
            //Should never reach here as it must have been a valid status
            assert (false);
            return null;
        }
    }
}

