package seedu.address.model.person;

/**
 * Represents the Teacher of the Module's Lecture or Tutorial.
 */
public class Teacher {
    public final String value;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param teacher A valid name of teacher.
     */
    public Teacher(String teacher) {
        //teacher is optional and hence we do not requireNonNull.
        //requireNonNull(deadline);
        value = teacher;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.Deadline // instanceof handles nulls
                && value.equals(((seedu.address.model.person.Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
