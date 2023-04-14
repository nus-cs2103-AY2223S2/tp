package seedu.address.model.module;

/**
 * Represents the Teacher of the Module's Lecture or Tutorial.
 */
public class Teacher {
    public static final String MESSAGE_CONSTRAINTS =
            "Teacher should only contain alphabet characters, spaces, periods, and commas. It should not be blank";
    public static final String VALIDATION_REGEX = "[a-zA-Z., ]+";
    public final String value;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param teacher A valid name of teacher.
     */
    public Teacher(String teacher) {
        // teacher is optional and hence we do not requireNonNull.
        // requireNonNull(teacher);
        value = teacher;
    }
    /**
     * Returns if a given string is a valid deadline.
     */
    public static boolean isValidTeacher(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.module.Teacher // instanceof handles nulls
                && value.equals(((seedu.address.model.module.Teacher) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
