package taa.model.student;

import java.util.function.Predicate;

import taa.model.ClassList;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class SameStudentPredicate implements Predicate<Student> {
    private final ClassList classList;

    public SameStudentPredicate(ClassList classList) {
        this.classList = classList;
    }

    @Override
    public boolean test(Student student) {
        return classList.hasStudent(student);
    }

}
