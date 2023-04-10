package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Represents an Internship's status in InternBuddy
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be one of the following: New, Applied, Assessment, Interview, Offered, "
                    + "Accepted or Rejected. It should not be blank too.";

    public static final String NEW = "new";
    public static final String APPLIED = "applied";
    public static final String ASSESSMENT = "assessment";
    public static final String INTERVIEW = "interview";
    public static final String OFFERED = "offered";
    public static final String REJECTED = "rejected";
    public static final String ACCEPTED = "accepted";
    //A set of valid statuses
    public static final List<String> LIST_OF_VALID_STATUSES =
            Arrays.asList(NEW, APPLIED, ASSESSMENT, INTERVIEW, OFFERED, REJECTED, ACCEPTED);
    public static final HashSet<String> SET_OF_VALID_STATUSES = new HashSet<String>(LIST_OF_VALID_STATUSES);

    public final String fullStatus;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status to be associated with an Internship.
     */
    public Status(String status) {
        requireNonNull(status);
        status = status.toLowerCase();
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.fullStatus = status;
    }


    /**
     * Returns true if a given string is a valid status
     *
     * @param test The string to check for.
     * @return true if the given string corresponds to a valid string for a role, else returns false.
     * @throws NullPointerException if a null status is passed in
     */
    public static boolean isValidStatus(String test) {
        if (test == null) {
            throw new NullPointerException();
        }
        return SET_OF_VALID_STATUSES.contains(test.toLowerCase());
    }

    /**
     * Returns the String representation of the Role.
     *
     * @return a String representing the role.
     */
    @Override
    public String toString() {
        return this.fullStatus;
    }


    /**
     * Determines if another object is equal to this Status object.
     *
     * @param other The other object to compare with.
     * @return true if the other object is a Status object with the same status string.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && fullStatus.equals(((Status) other).fullStatus)); // state check
    }

    /**
     * Gets the hash code of the Status object.
     *
     * @return the hash code for the status represented by the Status object.
     */
    @Override
    public int hashCode() {
        return fullStatus.hashCode();
    }
}

