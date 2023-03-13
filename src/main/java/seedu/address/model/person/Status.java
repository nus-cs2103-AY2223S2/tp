package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternshipApplication's status in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {
    enum StatusType {
        NA("NA"), PENDING("PENDING"), RECEIVED("RECEIVED"), REJECTED("REJECTED"),
        NO("NO");

        private String name;

        StatusType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only contain specific keywords and it should not be blank";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Status(String name) {
        requireNonNull(name);
        checkArgument(isValidStatus(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStatus(String test) {
        try {
            StatusType.valueOf(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && fullName.equals(((Status) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
