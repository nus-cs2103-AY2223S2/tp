package seedu.address.model.person.student;

import java.util.function.Predicate;

import seedu.address.model.person.Class;

/**
 * A predicate class that checks if the student belongs to this class
 */
public class ClassContainsKeywordsPredicate implements Predicate<Student> {
    private final Class sc;

    public ClassContainsKeywordsPredicate(Class sc) {
        this.sc = sc;
    }

    @Override
    public boolean test(Student student) {
        return sc.equals(student.getStudentClass());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other
                instanceof seedu.address.model.person.student.ClassContainsKeywordsPredicate // instanceof handles nulls
                && sc.equals(((seedu.address.model.person.student.ClassContainsKeywordsPredicate) other)
                .sc)); // state check
    }
}
