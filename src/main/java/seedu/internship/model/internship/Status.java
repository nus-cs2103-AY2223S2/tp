package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's Status in the internship catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    /**
     * The string representation of statuses. The statusId of each status type is its array index.
     * E.g. "INTERESTED" maps to statusId=0.
     */
    public static final String[] STATUS_TYPES = {"INTERESTED", "APPLIED", "OFFERED", "REJECTED"};


    public static final String MESSAGE_CONSTRAINTS =
            "Status of an Internship should be keyed in as a number from 0 to 3.";

    public final Integer statusId;

    /**
     * Constructs a {@code Status}.
     *
     * @param statusId A valid String that represents the id of a Status.
     */
    public Status(Integer statusId) {
        requireNonNull(statusId);
        checkArgument(isValidStatus(statusId), MESSAGE_CONSTRAINTS);
        this.statusId = statusId;
    }

    /**
     * Returns true if a given string represents a valid status id.
     * A valid status id is a number in the range of 0 - 3. Trailing space is allowed.
     */
    public static boolean isValidStatus(Integer test) {
        return test >= 0 && test <= 3;
    }

    @Override
    public String toString() {
        return STATUS_TYPES[statusId];
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && statusId.equals(((Status) other).statusId)); // state check
    }

    @Override
    public int hashCode() {
        return statusId.hashCode();
    }

}
