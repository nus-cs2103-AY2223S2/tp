package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given subject.
 */
public class NamePredicate implements Predicate<Student> {
    private final List<String> names;

    /**
     * Creates a predicate to test if a Homework's title matches the specified subject
     * @param names The names to test against.
     */
    public NamePredicate(List<String> names) {
        this.names = names;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param student The Student to test.
     * @return True if the student's name contains the given keyword.
     */
    @Override
    public boolean test(Student student) {
        for (String name : names) {
            if (student.getName().toString().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NamePredicate // instanceof handles nulls
            && names.equals(((NamePredicate) other).names)); // date check
    }
}
