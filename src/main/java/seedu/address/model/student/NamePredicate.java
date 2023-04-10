package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s name matches the given name.
 */
public class NamePredicate implements Predicate<Student> {
    private final List<String> names;

    /**
     * Creates a NamePredicate to test if a {@code Student}'s name matches the given name.
     *
     * @param names The name to test against.
     */
    public NamePredicate(List<String> names) {
        this.names = names;
    }

    /**
     * Tests if a {@code Student}'s name matches the given name.
     *
     * @param student The student to test.
     * @return True if the student's name matches the given name.
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
            || (other instanceof NamePredicate && names.equals(((NamePredicate) other).names));
    }
}
