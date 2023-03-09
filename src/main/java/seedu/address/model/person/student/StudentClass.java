package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * A StudentClass
 */
public class StudentClass {
    public static final String MESSAGE_CONSTRAINTS = "Student class must contain letters and/or digits "
            + "with no spaces in between";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param studentClass A valid student class.
     */
    public StudentClass(String studentClass) {
        requireNonNull(studentClass);
        checkArgument(isValidStudentClass(studentClass), MESSAGE_CONSTRAINTS);
        value = studentClass;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidStudentClass(String test) {

        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentClass// instanceof handles nulls
                && value.equals(((StudentClass) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
