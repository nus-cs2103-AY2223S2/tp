package tfifteenfour.clipboard.model.student;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's studentId in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student Ids should be alphanumeric with no underscores";
    public static final String VALIDATION_REGEX = "[^\\W_]+";

    public final String value;

    /**
     * Constructs an {@code StudentId}.
     *
     * @param studentId A valid studentId.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId.toUpperCase().trim();
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
