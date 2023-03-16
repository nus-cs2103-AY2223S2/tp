package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's status in the address book. Information contains his current year and course of study.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only contain alphanumeric characters, numbers and spaces, and it should not be blank";

    /*
     * The first character denotes the year of the student and must not be a whitespace,
     * while the second character denotes the course of the student and must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullStatusDetail;

    /**
     * Constructs a {@code Status}.
     *
     * @param statusDetail A valid status input of the student.
     */
    public Status(String statusDetail) {
        System.out.println("status checked pass");
        requireNonNull(statusDetail);
        checkArgument(isValidStatus(statusDetail), MESSAGE_CONSTRAINTS);
        fullStatusDetail = statusDetail;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullStatusDetail;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && fullStatusDetail.equals(((Status) other).fullStatusDetail)); // state check
    }

    @Override
    public int hashCode() {
        return fullStatusDetail.hashCode();
    }

}
