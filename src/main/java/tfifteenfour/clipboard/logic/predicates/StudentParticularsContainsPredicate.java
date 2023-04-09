package tfifteenfour.clipboard.logic.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class StudentParticularsContainsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public StudentParticularsContainsPredicate(String[] keywords) {
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getStudentId()
                .toString().toLowerCase().contains(keyword.toLowerCase()))
                || keywords.stream()
                .anyMatch(keyword -> student.getName()
                .toString().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentParticularsContainsPredicate // instanceof handles nulls
                && keywords.equals(((StudentParticularsContainsPredicate) other).keywords)); // state check
    }

}
