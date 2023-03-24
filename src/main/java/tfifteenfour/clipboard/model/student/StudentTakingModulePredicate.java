package tfifteenfour.clipboard.model.student;

import java.util.function.Predicate;

import tfifteenfour.clipboard.model.course.Course;


/**
 * Tests that a {@code Student}'s {@code modules} contain module code given.
 */
public class StudentTakingModulePredicate implements Predicate<Student> {
    private final String moduleToCheck;

    public StudentTakingModulePredicate(String moduleToCheck) {
        this.moduleToCheck = moduleToCheck;
    }

    @Override
    public boolean test(Student student) {
        for (Course module : student.getModules()) {
            String moduleString = module.getCourseCode();
            if (moduleString.equals(moduleToCheck)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentTakingModulePredicate // instanceof handles nulls
                && moduleToCheck.equals(((StudentTakingModulePredicate) other).moduleToCheck)); // state check
    }

}
