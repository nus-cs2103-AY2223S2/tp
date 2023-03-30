package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author wendy0107
/**
 * Represents a Subject in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSubjectName(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS = "Subject names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String subjectName;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject name.
     */
    public Subject(String subjectName) {
        requireNonNull(subjectName);
        checkArgument(isValidSubjectName(subjectName), MESSAGE_CONSTRAINTS);
        this.subjectName = subjectName;
    }

    /**
     * Returns true if a given string is a valid subject name.
     */
    public static boolean isValidSubjectName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + subjectName + ']';
    }

}
//@@author
